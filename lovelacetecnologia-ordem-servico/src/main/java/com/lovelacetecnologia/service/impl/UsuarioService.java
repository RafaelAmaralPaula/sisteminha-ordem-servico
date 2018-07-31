package com.lovelacetecnologia.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lovelacetecnologia.model.Usuario;
import com.lovelacetecnologia.service.IUsuario;
import com.lovelacetecnologia.util.ConexaoUtil;

public class UsuarioService implements IUsuario {

	@Override
	public void incluir(Usuario user) {

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "INSERT INTO usuario(username,senha,ativo)VALUES(?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setBoolean(3, user.isAtivo());

			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Usuario user) {

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "UPDATE usuario SET username = ?,senha = ? , ativo = ? WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setBoolean(3, user.isAtivo());
			statement.setInt(4, user.getId());

			statement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Usuario buscarPeloId(int id) {

		Usuario user = new Usuario();

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM usuario WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("senha"));
				user.setAtivo(rs.getBoolean("ativo"));

			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<Usuario> listarTodos() {

		List<Usuario> lista = new ArrayList<>();

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM usuario";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				Usuario user = new Usuario();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("senha"));
				user.setAtivo(rs.getBoolean("ativo"));

				lista.add(user);

			}

			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	@Override
	public void remover(Usuario usuario) {

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "DELETE FROM usuario WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, usuario.getId());

			statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Usuario> buscarPeloStatus(boolean status) {

		List<Usuario> lista = new ArrayList<>();
		Usuario usuario = new Usuario();

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM usuario WHERE ativo = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setBoolean(1, status);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				usuario.setId(resultSet.getInt("id"));
				usuario.setUsername(resultSet.getString("username"));
				usuario.setPassword(resultSet.getString("senha"));
				usuario.setAtivo(resultSet.getBoolean("ativo"));

				lista.add(usuario);

			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

}
