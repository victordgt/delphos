package br.org.universa.web;


import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import br.org.universa.negocio.RecursoTI;
import br.org.universa.negocio.SolucaoTI;
import br.org.universa.persistencia.DAO;

import com.google.inject.Inject;

public class EditaSolucaoTI extends BasePage {
	
	private static final long serialVersionUID = 1L;
	
	private FeedbackPanel feedback = new FeedbackPanel("feedback");
	private final SolucaoTI solucaoTI;
	
	@Inject
	private DAO<SolucaoTI> dao;
	

	@SuppressWarnings("serial")
	public EditaSolucaoTI(SolucaoTI solucaoTI, final Page pagina) {
		this.solucaoTI = solucaoTI;
		//PAINEL COM AS MENSAGENS DE VALIDAÇÃO DO FORMULÁRIO
		add(feedback.setVisible(false)); 
		
     	Form<SolucaoTI> form = new Form<SolucaoTI>("form", new CompoundPropertyModel<SolucaoTI>(this.solucaoTI));
    	form.add(new TextField<String>("titulo"));
    	
    	final TextField<String> versao = new TextField<String>("versao");
    	versao.setRequired(true);
    	versao.setOutputMarkupId(true); // habilita ajax
    	
       	final WebMarkupContainer containerVersao = new WebMarkupContainer("container_versao");
    	containerVersao.setOutputMarkupId(true);   	
    	
    	final TextField<String> categoria = new TextField<String>("categoria");
    	categoria.setEnabled(false);
    	
    	
    	form.add(categoria);
    	
    	
    	form.add(new RequiredTextField<String>("nome"));
    	
    	containerVersao.add(versao);
    	form.add(containerVersao);
    	
    	if (solucaoTI.getCategoria().equals(RecursoTI.SOFTWARE.toString())) {
			containerVersao.add(new AttributeModifier("class", new Model<String>("visivel")));
			versao.setRequired(true);    		
    	} else {
			containerVersao.add(new AttributeModifier("class", new Model<String>("hidden")));
			versao.setRequired(false);     		
    	}
    	
    	TextArea<String> descricao = new TextArea<String>("descricao");
    	descricao.setRequired(true);
    	
		form.add(descricao);
    	TextArea<String> solucao = new TextArea<String>("solucao");
    	solucao.setRequired(true);
		form.add(solucao);
    	
    	form.add(new Button("cadastra") {
    		@Override
    		public void onSubmit() {
    			
    			SolucaoTI cadastroBase = (SolucaoTI)getForm().getModelObject();
    			super.onSubmit();
       			try {
       				dao.salvaOuAltera(cadastroBase);
      				setResponsePage(pagina);
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
            	setResponsePage(pagina);
            }
        });    	
    	add(form);
	}
	
	
}
