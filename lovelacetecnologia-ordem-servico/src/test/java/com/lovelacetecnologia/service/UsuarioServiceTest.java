package com.lovelacetecnologia.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lovelacetecnologia.model.Usuario;
import com.lovelacetecnologia.service.impl.UsuarioService;

public class UsuarioServiceTest {

	private IUsuario service;

	@Before
	public void setup() {
		service = new UsuarioService();
	}

	@Test
	public void deveIncluirUsuarioNoBancoDeDados() {

		int antes = service.listarTodos().size();

		Usuario usuario = new Usuario("normandes junior", "345", false);
		service.incluir(usuario);

		int depois = service.listarTodos().size();

		assertEquals(antes + 1, depois);

	}

	@Test
	public void devePermitirAlterarUsernameDoUsuarioNoBancoDeDados() {

		String username = "laura";
		String username2 = "natasha";

		Usuario usuario = create(username, "373475", true);

		Integer id = usuario.getId();
		assertNotNull(id);
		assertTrue(id.intValue() > 0);

		Usuario encontrado = service.buscarPeloId(id);

		assertEquals(encontrado.getUsername(), username);

		encontrado.setUsername(username2);
		encontrado.setAtivo(false);
		encontrado.setPassword("dsgfdgfn");

		service.alterar(encontrado);

		Usuario encontrado2 = service.buscarPeloId(id);

		assertEquals(encontrado2.getUsername(), username2);
		assertNotEquals(encontrado2.getUsername(), username);

	}

	@Test
	public void devePermitirAlterarSenhaDoUsuarioNoBancoDeDados() {

		String senha = "dffdh";
		String senha2 = "raegr";

		Usuario usuario = create("Hugo", senha, false);

		Integer id = usuario.getId();
		assertTrue(id.intValue() > 0);
		assertNotNull(id);

		Usuario encontrado = service.buscarPeloId(id);

		assertEquals(encontrado.getPassword(), senha);

		encontrado.setUsername("Samuel");
		encontrado.setPassword(senha2);
		encontrado.setAtivo(true);
		service.alterar(encontrado);

		Usuario encontrado2 = service.buscarPeloId(id);

		assertEquals(encontrado2.getPassword(), senha2);
		assertNotEquals(encontrado2.getPassword(), senha);

	}

	@Test
	public void devePermitirAlterarStatusDoUsuarioNoBancoDeDados() {

		boolean status = true;
		boolean status2 = false;

		Usuario usuario = create("Daniel", "4465", status);

		Integer id = usuario.getId();
		assertTrue(id.intValue() > 0);
		assertNotNull(id);

		Usuario encontrado = service.buscarPeloId(id);

		assertEquals(encontrado.isAtivo(), status);

		encontrado.setUsername("Yuri");
		encontrado.setPassword("12244235");
		encontrado.setAtivo(status2);
		service.alterar(encontrado);

		Usuario encontrado2 = service.buscarPeloId(id);

		assertEquals(encontrado2.isAtivo(), status2);
		assertNotEquals(encontrado2.isAtivo(), status);

	}

	@Test
	public void devePermitiBuscarUsuarioNoBancoDeDadosPeloId() {

		Usuario usuario = create("gabriel", "373475", true);

		assertNotNull(service.buscarPeloId(usuario.getId()).getId());

	}

	@Test
	public void deveListarTodosOsUsuariosDoBancoDeDados() {

		int antes = service.listarTodos().size();

		 create("teste", "dssvg", false);

		int depois = service.listarTodos().size();

		assertEquals(antes + 1, depois);

	}

	@Test
	public void devePermitirRemoverUsuarioDoBancoDeDadosRemover() {

		Usuario usuario = create("fatima", "wrsd", true);

		int antes = service.listarTodos().size();

		service.remover(usuario);

		int depois = service.listarTodos().size();

		assertEquals(antes - 1, depois);

	}

	@Test
	public void devePermitirBuscaUsuarioPeloStausEQuantidadeDeAtivosInativos() throws Exception {
		
		removerTodos();
		
		create("Renam", "363473", true);
		
		create("Roberto", "3ew3", true);
		
		create("Luiz", "473", true);
		
		create("João", "324473", false);
		
		create("Sandro", "47w5fd3", false);

		int ativos = service.buscarPeloStatus(true).size();
		int inativos = service.buscarPeloStatus(false).size();

		assertTrue(ativos == 3);
		assertTrue(inativos == 2);

	}

	private Usuario create(String userName, String senha, boolean ativo) {

		Usuario usuario = new Usuario(userName, senha, ativo);

		service.incluir(usuario);

		List<Usuario> usuarios = service.listarTodos();

		return service.listarTodos().get(usuarios.size() - 1);

	}

	private void removerTodos() {
		service.listarTodos().forEach(user-> {
			service.remover(user);
		});

	}

}
