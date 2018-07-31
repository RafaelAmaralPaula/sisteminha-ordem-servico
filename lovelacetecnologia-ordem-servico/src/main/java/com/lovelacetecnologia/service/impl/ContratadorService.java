package com.lovelacetecnologia.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lovelacetecnologia.model.Contratador;
import com.lovelacetecnologia.service.IContratador;
import com.lovelacetecnologia.util.ConexaoUtil;

public class ContratadorService implements IContratador {

	@Override
	public void incluir(Contratador contratador) {

		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "INSERT INTO contratador (nome,email) VALUES(?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, contratador.getNome());
			statement.setString(2, contratador.getEmail());

			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Contratador contratador) {

		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "UPDATE contratador SET nome = ? , email = ?  WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, contratador.getNome());
			statement.setString(2, contratador.getEmail());
			statement.setInt(3, contratador.getId());

			System.out.println("ALTERADO COM SUCESSO !");
			
			statement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Contratador buscarPeloId(int id) {

		Contratador contratador = new Contratador();

		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM  contratador WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				contratador.setId(resultSet.getInt("id"));
				contratador.setNome(resultSet.getString("nome"));
				contratador.setEmail(resultSet.getString("email"));

			}

			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contratador;
	}

	@Override
	public List<Contratador> listarTodos() {

		List<Contratador> lista = new ArrayList<>();

		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM  contratador";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Contratador contratador = new Contratador();

				contratador.setId(resultSet.getInt("id"));
				contratador.setNome(resultSet.getString("nome"));
				contratador.setEmail(resultSet.getString("email"));

				lista.add(contratador);

			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void remover(Contratador contratador) {

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "DELETE FROM contratador WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, contratador.getId());

			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
