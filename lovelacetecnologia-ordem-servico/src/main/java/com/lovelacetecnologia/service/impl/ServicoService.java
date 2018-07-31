package com.lovelacetecnologia.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lovelacetecnologia.exception.OrdemServicoException;
import com.lovelacetecnologia.model.Servico;
import com.lovelacetecnologia.model.Status;
import com.lovelacetecnologia.service.IServico;
import com.lovelacetecnologia.util.ConexaoUtil;

public class ServicoService implements IServico {

	@Override
	public void incluir(Servico servico) throws OrdemServicoException {

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "INSERT INTO servico (status,descricao,preco) VALUES(?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, servico.getStatus().toString());
			statement.setString(2, servico.getDescricao());
			statement.setDouble(3, servico.getPreco());

			statement.execute();
			connection.close();

			System.out.println("SERVICO INSERIDO COM SUCESSO !");

		} catch (Exception e) {
			throw new OrdemServicoException(e);
		}

	}

	@Override
	public void alterar(Servico servico) throws OrdemServicoException {

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "UPDATE servico SET status = ? , descricao = ? , preco = ? WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, servico.getStatus().toString());
			statement.setString(2, servico.getDescricao());
			statement.setDouble(3, servico.getPreco());
			statement.setInt(4, servico.getId());

			System.out.println("ALTERADO COM SUCESSO !");

			statement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			throw new OrdemServicoException(e);
		}

	}

	@Override
	public Servico buscarPeloId(int id) {

		Servico servico = new Servico();

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM servico WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				servico.setId(resultSet.getInt("id"));
				servico.setStatus(Status.valueOf(resultSet.getString("status")));
				servico.setDescricao(resultSet.getString("descricao"));
				servico.setPreco(resultSet.getDouble("preco"));
			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return servico;
	}

	@Override
	public List<Servico> listarTodos() {

		List<Servico> lista = new ArrayList<>();

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM servico";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet set = statement.executeQuery();

			while (set.next()) {

				Servico servico = new Servico();

				servico.setId(set.getInt("id"));
				servico.setStatus(Status.valueOf(set.getString("status")));
				servico.setDescricao(set.getString("descricao"));
				servico.setPreco(set.getDouble("preco"));

				lista.add(servico);
			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void remover(Servico servico) {

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "DELETE FROM servico WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, servico.getId());

			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Servico> buscarPeloStatus(Status status) {

		List<Servico> lista = new ArrayList<>();
		Servico serv = new Servico();

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM servico WHERE status = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, status.toString());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				serv.setId(resultSet.getInt("id"));
				serv.setDescricao(resultSet.getString("descricao"));
				serv.setPreco(resultSet.getDouble("preco"));
				serv.setStatus(Status.valueOf(resultSet.getString("status")));
				
				lista.add(serv);

			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

}
