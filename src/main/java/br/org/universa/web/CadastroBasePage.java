package br.org.universa.web;


import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
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
import br.org.universa.persistencia.DAO;

import com.google.inject.Inject;



public class CadastroBasePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	private FeedbackPanel feedback = new FeedbackPanel("feedback");
	private CadastroBase cadastroBase = new CadastroBase();
	private List<String> categorias = Arrays.asList(new String[]{"SOFTWARE", "HARDWARE"});
	
	@Inject
	private DAO<CadastroBase> dao;


	@SuppressWarnings("serial")
	public CadastroBasePage() {
		
		//PAINEL COM AS MENSAGENS DE VALIDAÇÃO DO FORMULÁRIO
		add(feedback.setVisible(false)); 
		
     	Form<Object> form = new Form<Object>("form");
    	form.add(new TextField<String>("titulo", new PropertyModel<String>(this,"cadastroBase.titulo")));
    	
    	final TextField<String> versao = new TextField<String>("versao", new PropertyModel<String>(this,"cadastroBase.versao"));
    	versao.setRequired(true);
    	versao.setOutputMarkupId(true); // habilita ajax
    	
    	final DropDownChoice<String> choice = new DropDownChoice<String>("categoria", new PropertyModel<String>(this, "cadastroBase.categoria"), categorias);
    	choice.setRequired(true);
    	form.add(choice);
    	
    	choice.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			private static final long serialVersionUID = 1L;

			@Override
            protected void onUpdate(AjaxRequestTarget target) {
				if (choice.getValue().equals("0")) {
					versao.setEnabled(true);					
				} else {
					versao.setEnabled(false);
				}
				
				target.addComponent(versao);
	
            }
        });
    	
    	
    	
    	
    	form.add(new TextField<String>("nome", new PropertyModel<String>(this,"cadastroBase.nome")).setRequired(true));
    	
		form.add(versao);
    	form.add(new TextArea<String>("descricao", new PropertyModel<String>(this, "cadastroBase.descricao")).setRequired(true));
    	form.add(new TextArea<String>("solucao", new PropertyModel<String>(this, "cadastroBase.solucao")).setRequired(true));
    	
    	form.add(new Button("cadastra") {
    		@Override
    		public void onSubmit() {
    			super.onSubmit();
       			try {
       				dao.salvaOuAltera(cadastroBase);
      				setResponsePage(CadastroBasePage.class);
    				info("Cadastro salvo com sucesso!"); //TODO Fazer aparecer o cadastro salvo quando já fez o redirect
  
       			} catch(RuntimeException ex) {
       				getSession().info(ex.getMessage());     				
       			}
    		}
    		
    		@Override
    		protected void onInvalid() {
    			super.onInvalid();
    			feedback.setVisible(true);
    		}
    		
    	});
    	
    	form.add(new Link<Object>("cancela") {
            @Override
            public void onClick() {
            	setResponsePage(Menu.class);
            }
        });    	
    	add(form);
	}
}
