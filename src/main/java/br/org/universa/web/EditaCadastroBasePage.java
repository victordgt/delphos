package br.org.universa.web;


import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.CadastroBase;
import br.org.universa.persistencia.DAO;

import com.google.inject.Inject;

public class EditaCadastroBasePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	private FeedbackPanel feedback = new FeedbackPanel("feedback");
	private final CadastroBase cadastroBase;
	private List<String> categorias = Arrays.asList(new String[]{"SOFTWARE", "HARDWARE"});
	
	@Inject
	private DAO<CadastroBase> dao;
	

	@SuppressWarnings("serial")
	public EditaCadastroBasePage(CadastroBase cadastroBase) {
		this.cadastroBase = cadastroBase;
		//PAINEL COM AS MENSAGENS DE VALIDAÇÃO DO FORMULÁRIO
		add(feedback.setVisible(false)); 
		
     	Form<CadastroBase> form = new Form<CadastroBase>("form", new CompoundPropertyModel<CadastroBase>(this.cadastroBase));
    	form.add(new TextField<String>("titulo"));
    	
    	final TextField<String> versao = new TextField<String>("versao");
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
    	
    	
    	form.add(new RequiredTextField<String>("nome"));
    	
		form.add(versao);
    	TextArea<String> descricao = new TextArea<String>("descricao");
    	descricao.setRequired(true);
    	
		form.add(descricao);
    	TextArea<String> solucao = new TextArea<String>("solucao");
    	solucao.setRequired(true);
		form.add(solucao);
    	
    	form.add(new Button("cadastra") {
    		@Override
    		public void onSubmit() {
    			
    			CadastroBase cadastroBase = (CadastroBase)getForm().getModelObject();
    			super.onSubmit();
       			try {
       				dao.salvaOuAltera(cadastroBase);
      				setResponsePage(ListaBaseConhecimento.class);
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
            	setResponsePage(ListaBaseConhecimento.class);
            }
        });    	
    	add(form);
	}
	
	
}
