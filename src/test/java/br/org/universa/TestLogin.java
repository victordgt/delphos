package br.org.universa;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import br.org.universa.web.Login;
import br.org.universa.web.PaginaFluxoInicial;

public class TestLogin {
	
	private WicketTester tester = new WicketTester(Login.class);
	
	
	@Test
	public void testAutenticar() {	
		tester.startPage(Login.class);
		
		FormTester form = tester.newFormTester("loginForm");
		form.setValue("userId", "usuario");
		form.setValue("password", "senha");
		form.submit();
		
		tester.assertRenderedPage(PaginaFluxoInicial.class);
	}
	
	@Test
	public void testFalhaAutenticacao() {	
		tester.startPage(Login.class);
		
		FormTester form = tester.newFormTester("loginForm");
		form.setValue("userId", "");
		form.setValue("password", "");
		form.submit();
		
		tester.assertRenderedPage(Login.class);
	}	

}
