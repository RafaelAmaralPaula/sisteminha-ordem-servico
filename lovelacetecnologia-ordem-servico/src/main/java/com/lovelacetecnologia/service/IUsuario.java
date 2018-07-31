package com.lovelacetecnologia.service;

import java.util.List;

import com.lovelacetecnologia.model.Usuario;

public interface IUsuario {

	public void incluir(Usuario user);

	public void alterar(Usuario user);

	public Usuario buscarPeloId(int id);

	public List<Usuario> listarTodos();

	public List<Usuario> buscarPeloStatus(boolean status);

	public void remover(Usuario usuario);

}
