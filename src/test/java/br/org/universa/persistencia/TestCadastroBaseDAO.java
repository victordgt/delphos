package br.org.universa.persistencia;

import org.junit.Test;

import br.org.universa.negocio.CadastroBase;

public class TestCadastroBaseDAO {
	
	private CadastroBaseDAO dao = new CadastroBaseDAO();
	
	@Test
	public void testeIncluir()  {
		CadastroBase cadastro = new CadastroBase();
		
		cadastro.setCategoria("HARDWARE");
		cadastro.setDescricao("Problema de segurança na rede");
		cadastro.setSolucao("Instalar um firewall na rede");
		cadastro.setNome("Firewall");
		cadastro.setTitulo("Firewall");
		
		dao.salvaOuAltera(cadastro);
		
		
	}

}
