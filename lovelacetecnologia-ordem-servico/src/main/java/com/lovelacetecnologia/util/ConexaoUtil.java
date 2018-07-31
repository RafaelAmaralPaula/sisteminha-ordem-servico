package com.lovelacetecnologia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {

	private static ConexaoUtil conexao;

	public static ConexaoUtil getInstance() {
		if (conexao == null) {
			conexao = new ConexaoUtil();
		}

		return conexao;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "teste", "teste");
	}

}
