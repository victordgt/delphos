package br.org.universa.web;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class TestCadastroBase {
	
	private WicketTester tester = new WicketTester(Login.class);
	
	@Test
	public void testCadastrarForm() {	
		tester.startPage(CadastroBasePage.class);
		
		FormTester form = tester.newFormTester("form");
		form.setValue("titulo", "titulo");
		form.setValue("categoria", "HARDWARE");
		form.setValue("versao", "versao");
		form.setValue("descricao", "descricao");
		form.setValue("solucao", "solucao");
		form.submit();
		
		//TODO Verificar a mensagem do Feedbackpanel para ver ser cadastrou com sucesso
		tester.assertRenderedPage(CadastroBasePage.class);
    			
	}
	

}
