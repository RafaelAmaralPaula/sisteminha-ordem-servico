package com.lovelacetecnologia.service;

import java.util.List;

import com.lovelacetecnologia.model.Contratador;

public interface IContratador {
	
	public void incluir(Contratador contratador);

	public void alterar(Contratador contratador);

	public Contratador buscarPeloId(int id);

	public List<Contratador> listarTodos();

	public void remover(Contratador contratador);

}
