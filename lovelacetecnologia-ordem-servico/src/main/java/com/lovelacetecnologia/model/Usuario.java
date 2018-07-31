package com.lovelacetecnologia.model;

public class Usuario {

	private int id;
	private String username;
	private String password;
	private boolean ativo;

	public Usuario() {

	}

	public Usuario(int id, String username, String password, boolean ativo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.ativo = ativo;
	}

	public Usuario(String username, String password , boolean ativo) {
		this.username = username;
		this.password = password;
		this.ativo = ativo;
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
