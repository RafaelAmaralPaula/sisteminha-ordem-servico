package com.lovelacetecnologia.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lovelacetecnologia.model.Contratador;
import com.lovelacetecnologia.service.impl.ContratadorService;

public class ContratadorServiceTest {

	private IContratador service;

	@Before
	public void setup() {
		service = new ContratadorService();
	}

	@Test
	public void deveAdicionarContratadorNoBancoDeDados() {

		int antes = service.listarTodos().size();

		Contratador contratador = new Contratador();
		contratador.setNome("Mauro Sergio");
		contratador.setEmail("mauro@sergio.com");

		service.incluir(contratador);

		int depois = service.listarTodos().size();

		assertEquals(antes + 1, depois);

	}

	@Test
	public void devePermitirAlterarNomeDoContratadorNoBancoDeDados() {
		
		String nome = "marcelo";
		String nome2 = "afonso";
		
		Contratador contratador = create(nome,"marcelo@gmail.com");
	
		
		Integer id = contratador.getId();
		assertNotNull(id);
		assertTrue(id.intValue() > 0);	
		
		Contratador encontrado = service.buscarPeloId(id);
		
		assertEquals(encontrado.getNome(),nome);
		
		encontrado.setNome(nome2);
		encontrado.setEmail("afonso@afonso.com");
		
		service.alterar(encontrado);

		Contratador encontrado2 = service.buscarPeloId(id);

		assertEquals(encontrado2.getNome() ,nome2);
		assertNotEquals(encontrado2.getNome(), nome); 

	}
	
	@Test
	public void deveAlterarEmailDosContratadoresDoServicoNoBancoDeDados() throws Exception {
		
		String email ="gai@gabi.com";
		String email2 = "fatima@fatima.com"	;
		
		Contratador contratador = create("Gabriel", email);
		
		Integer id = contratador.getId();
		assertNotNull(id);
		assertTrue(id > 0);
		
		Contratador encontrado = service.buscarPeloId(id);
		
		assertEquals(encontrado.getEmail(), email);
		
		encontrado.setNome("Fatima");
		encontrado.setEmail(email2);
		
		service.alterar(encontrado);
		
		Contratador encontrado2 = service.buscarPeloId(id);
		
		assertEquals(encontrado2.getEmail(), email2);
		assertNotEquals(encontrado2.getEmail(), email);
		
		
		
	}

	@Test
	public void deveBuscarContratadorPeloIdNoBancoDeDados() {

		Contratador contratador = new Contratador();
		contratador.setNome("mauro sergio");
		contratador.setEmail("mauro@sergio.com");

		service.incluir(contratador);

		assertNotNull(service.buscarPeloId(contratador.getId()).getId());

	}

	@Test
	public void devePermitirListarTodosContratadoresNoBancoDeDados() {

		int antes = service.listarTodos().size();
		
		Contratador contratador = new Contratador();
		contratador.setNome("Guilherme");
		contratador.setEmail("guilherme@guilherme.com");
		
		service.incluir(contratador);
		
		int depois = service.listarTodos().size();
		
		assertEquals(antes + 1,depois);
	}

	@Test
	public void devePermitirRemoverContratadoresNoBancoDeDados() {


		Contratador contratador = create("Mauro Sergio De Paula","mauro@sergio.com");		
		
		int antes = service.listarTodos().size();
		
		service.remover(contratador);
	
		int depois = service.listarTodos().size();

		assertEquals(antes - 1, depois);

	}
	
	private Contratador create(String nome , String email){
		
		Contratador contratador = new Contratador();
		contratador.setNome(nome);
		contratador.setEmail(email);
		
		service.incluir(contratador);
		
		List<Contratador> contratadores = service.listarTodos();
		
		return service.listarTodos().get(contratadores.size() - 1);
		
	}
	
	

}
