package com.lovelacetecnologia.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lovelacetecnologia.exception.OrdemServicoException;
import com.lovelacetecnologia.model.Servico;
import com.lovelacetecnologia.model.Status;
import com.lovelacetecnologia.service.impl.ServicoService;

public class ServicoServiceTest extends ServicoService {

	private IServico service;

	@Before
	public void setup() {
		service = new ServicoService();
	}

	@Test
	public void devePerimitirAdicionarServicoNoBancoDeDados() throws OrdemServicoException {

		int antes = service.listarTodos().size();

		Servico s1 = new Servico();
		s1.setStatus(Status.CANCELADO);
		s1.setDescricao("Trocar Placa Mãe Do PC");
		s1.setPreco(600.00);

		service.incluir(s1);

		int depois = service.listarTodos().size();

		assertEquals(antes + 1, depois);

	}

	@Test
	public void devePerimitirRemoverrServicosDoBancoDeDados() throws OrdemServicoException {

		Servico servico = create("Trocar Placa Mãe Do PC", Status.CANCELADO, 600d);

		int antes = service.listarTodos().size();
		service.remover(servico);
		int depois = service.listarTodos().size();

		assertEquals(antes - 1, depois);
	}

	@Test
	public void deveListarQuantidadeDeServicoNoBancoDeDados() throws OrdemServicoException {

		int antes = service.listarTodos().size();

		Servico servico = new Servico();

		servico.setDescricao("Teste");
		servico.setPreco(400.00);
		servico.setStatus(Status.CANCELADO);

		service.incluir(servico);

		int depois = service.listarTodos().size();

		assertEquals(antes + 1, depois);

	}

	@Test
	public void deveListarServicosPeloStatusEQauntidadeDePendenteCanceladoETerminado() throws Exception {

		removeAll();
		
		create("Teste", Status.CANCELADO,400.00);
		
		create("Teste8927989", Status.CANCELADO, 600.00);
		
		create("usajgjxggdus",Status.TERMINADO, 300.00);
		
		create("tuyurfdsv", Status.PENDENTE, 50d);
		
		create("bbbmmm", Status.PENDENTE, 100.00);
		
		int pendente = service.buscarPeloStatus(Status.PENDENTE).size();
		int terminado = service.buscarPeloStatus(Status.TERMINADO).size();
		int cancelado = service.buscarPeloStatus(Status.CANCELADO).size();

		assertTrue(cancelado == 2);
		assertTrue(pendente == 2);
		assertTrue(terminado == 1);

	}

	@Test
	public void devePerimitirIncluirEAlterarADescricaoDosServicosNoBancoDeDados() throws OrdemServicoException {

		String desc = "Configuração Firewall";
		String desc2 = "Teste 02";

		Servico servico = create(desc, Status.TERMINADO, 900d);

		Integer id = servico.getId();
		Assert.assertNotNull(id);
		Assert.assertTrue(id.intValue() > 0);

		Servico encontrado = service.buscarPeloId(id);

		Assert.assertEquals(encontrado.getDescricao(), desc);

		encontrado.setStatus(Status.PENDENTE);
		encontrado.setDescricao(desc2);
		encontrado.setPreco(100.00);
		service.alterar(encontrado);

		Servico encontrado2 = service.buscarPeloId(id);

		Assert.assertEquals(encontrado2.getDescricao(), desc2);
		Assert.assertNotEquals(encontrado2.getDescricao(), desc);
	}

	@Test
	public void devePermitirAlterarStatusDosServicosNoBancoDeDados() throws OrdemServicoException {

		Status status = Status.PENDENTE;
		Status status2 = Status.TERMINADO;

		Servico servico = create("Tp-link", status, 1000d);

		Integer id = servico.getId();
		assertNotNull(id);
		assertTrue(id.intValue() > 0);

		Servico encontrado = service.buscarPeloId(id);

		assertEquals(encontrado.getStatus(), status);

		encontrado.setStatus(status2);
		encontrado.setDescricao("Teste 0345");
		encontrado.setPreco(300.00);
		service.alterar(encontrado);

		Servico encontrado2 = service.buscarPeloId(id);

		assertEquals(encontrado2.getStatus(), status2);
		assertNotEquals(encontrado2.getStatus(), status);

	}

	@Test
	public void devePermitirAlterarOPrecoDosServicoNoBancosDeDados() throws OrdemServicoException {

		Double preco = 20d;
		Double preco2 = 50d;

		Servico servico = create("Atualização Do Windows", Status.TERMINADO, preco);

		Integer id = servico.getId();
		assertNotNull(id);
		assertTrue(id.intValue() > 0);

		Servico encontrado = service.buscarPeloId(id);

		assertEquals(encontrado.getPreco(), preco);

		encontrado.setDescricao("Atualização Do Linux");
		encontrado.setStatus(Status.PENDENTE);
		encontrado.setPreco(preco2);

		service.alterar(encontrado);

		Servico encontrado2 = service.buscarPeloId(id);

		assertEquals(encontrado2.getPreco(), preco2);
		assertNotEquals(encontrado2.getPreco(), preco);

	}

	@Test
	public void devePerimitirBuscarUmServicosNoBancoDeDadosPeloId() throws OrdemServicoException {

		Servico s = new Servico();
		s.setDescricao("Sabonete");
		s.setStatus(Status.CANCELADO);
		s.setPreco(500.00);

		service.incluir(s);

		assertNotNull(service.buscarPeloId(s.getId()).getId());

	}

//	 @Test
//	 public void deveAdicionarServicoNoBancoDeDadosDescricaoNaoInformada() throws
//	 OrdemServicoException {
//	
//	 int antes = service.listarTodos().size();
//	
//	 Servico s1 = new Servico();
//	 s1.setStatus(Status.CANCELADO);
//	 s1.setPreco(600.00);
//	 service.incluir(s1);
//	
//	 int depois = service.listarTodos().size();
//	
//	 assertEquals(antes + 1, depois);
//	 }

	private Servico create(String desc, Status status, Double preco) throws OrdemServicoException {

		Servico servico = new Servico();
		servico.setDescricao(desc);
		servico.setStatus(status);
		servico.setPreco(preco);

		service.incluir(servico);

		List<Servico> servicos = service.listarTodos();

		return service.listarTodos().get(servicos.size() - 1);
	}

	private void removeAll() throws OrdemServicoException {
		service.listarTodos().forEach(servico->{
			service.remover(servico);
			
		});
	}

}
