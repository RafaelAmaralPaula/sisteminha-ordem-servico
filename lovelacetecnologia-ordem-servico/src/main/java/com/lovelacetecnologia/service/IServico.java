package com.lovelacetecnologia.service;

import java.util.List;

import com.lovelacetecnologia.exception.OrdemServicoException;
import com.lovelacetecnologia.model.Servico;
import com.lovelacetecnologia.model.Status;

public interface IServico {
	
	public void incluir(Servico servico) throws OrdemServicoException;

	public void alterar(Servico servico) throws OrdemServicoException;

	public Servico buscarPeloId(int id);

	public List<Servico> listarTodos();
	
	public List<Servico> buscarPeloStatus(Status status);

	public void remover(Servico servico);
	
}
