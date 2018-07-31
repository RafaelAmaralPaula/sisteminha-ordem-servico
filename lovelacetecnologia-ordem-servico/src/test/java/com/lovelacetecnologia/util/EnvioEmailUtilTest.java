package com.lovelacetecnologia.util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;

import com.lovelacetecnologia.model.Contratador;
import com.lovelacetecnologia.model.EnviarEmail;
import com.lovelacetecnologia.service.IContratador;
import com.lovelacetecnologia.service.impl.ContratadorService;

public class EnvioEmailUtilTest {

	private EnviadorDeEmailUtil email;
	private IContratador service;
	private LocalDate data;
	private LocalTime hora;

	@Before
	public void setup() {
		email = new EnviadorDeEmailUtil();
		service = new ContratadorService();
		data = LocalDate.now();
		hora = LocalTime.now();

	}

	@Test
	public void devePermitirEnviarIncluirUmContratadorEEnviarOEmailSemAnexo() throws EmailException {

		String date = FormatadoraNumerosDatasHorasUtil.date(data);
		String time = FormatadoraNumerosDatasHorasUtil.time(hora);
		
		Contratador c = create("teste@teste.com", "teste");

		Integer id = c.getId();
		assertNotNull(id);
		assertTrue(id.intValue() > 0);

		Contratador encontrado = service.buscarPeloId(id);

		String mensagem = mensagem(encontrado,date,time);
		String para = encontrado.getEmail();

		EnviarEmail enviarEmail = new EnviarEmail(mensagem, "Termino Do Serviço Pedido", para);
		email.enviarSemAnexo(enviarEmail);

	}
	

	@Test
	public void devePermitirEnviarIncluirUmContratadorEEnviarOEmailComFoto() throws EmailException {

		String date = FormatadoraNumerosDatasHorasUtil.date(data);
		String time = FormatadoraNumerosDatasHorasUtil.time(hora);
		
		Contratador c = create("teste@teste.com", "teste");
		
		Integer id = c.getId();
		assertNotNull(id);
		assertTrue(id.intValue() > 0);

		Contratador encontrado = service.buscarPeloId(id);

		String mensagem = mensagem(encontrado,date,time);
		String para = encontrado.getEmail();

		EnviarEmail enviarEmail = new EnviarEmail(mensagem, "Término Do Serviço Pedido", para);
		email.enviarEmailComAnexo(enviarEmail);

	}
	

	private Contratador create(String email, String nome) {

		Contratador contratador = new Contratador();
		contratador.setNome(nome);
		contratador.setEmail(email);
		service.incluir(contratador);

		List<Contratador> contratadores = service.listarTodos();

		return service.listarTodos().get(contratadores.size() - 1);
	}

	private String mensagem(Contratador c , String data , String horas) {

		String mensagem = (" - Olá , Tudo Bem ? \n\n * Lovelace Tecnologia vêm informar ao Senhor(a), " + c.getNome()
				+ " através desse e-mail que já terminamos serviço solicitado. " + "\n\n " +
				"   * Data do término da tarefa - > " + data + " \n\n" +
				"    * Hora do término -> " + horas + " \n\n" +
				"    * Acesse Nosso Site -> http://lovelacetecnologia.com/page/ \n\n" +   
				"  Um Abraço ! \n\n" + "  Lovelace Tecnologia ");

		return mensagem;
	}

}
