package br.org.universa.web;


import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.CadastroBase;


public class CadastroBasePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	private CadastroBase cadastroBase = new CadastroBase();

	private List<String> categorias = Arrays.asList(new String[]{"SOFTWARE", "HARDWARE"});


	@SuppressWarnings("serial")
	public CadastroBasePage() {
		
		//PAINEL COM AS MENSAGENS DE VALIDAÇÃO DO FORMULÁRIO
		add(new FeedbackPanel("feedback")); 
		
     	Form<Object> form = new Form<Object>("form");
    	form.add(new TextField<String>("titulo", new PropertyModel<String>(this,"cadastroBase.titulo")));
    	
    	DropDownChoice<String> choice=new DropDownChoice<String>("categoria", new PropertyModel<String>(this, "cadastroBase.categoria"), categorias);
    	choice.setRequired(true);
    	form.add(choice);
    	
    	
    	form.add(new TextField<String>("nome", new PropertyModel<String>(this,"cadastroBase.nome")).setRequired(true));
    	form.add(new TextField<String>("versao", new PropertyModel<String>(this,"cadastroBase.versao")).setRequired(true));
    	form.add(new TextArea<String>("descricao", new PropertyModel<String>(this, "cadastroBase.descricao")).setRequired(true));
    	form.add(new TextArea<String>("solucao", new PropertyModel<String>(this, "cadastroBase.solucao")).setRequired(true));
    	
    	form.add(new Button("cadastra") {
    		@Override
    		public void onSubmit() {
    			
    			info("Cadastro salvo com sucesso!");
    			
    		}
    		
    	});
    	
    	form.add(new Link<Object>("cancela") {
            @Override
            public void onClick() {
            	setResponsePage(PaginaFluxoInicial.class);
            }
        });    	
    	add(form);
	}
}
