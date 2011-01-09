package br.org.universa.web;


import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.RecursoTI;
import br.org.universa.negocio.SolucaoTI;
import br.org.universa.negocio.controller.SolucaoTIBC;

import com.google.inject.Inject;

public class InsereSolucaoTI extends BasePage {
	
	private static final long serialVersionUID = 1L;
	
	private FeedbackPanel feedback = new FeedbackPanel("feedback");
	private SolucaoTI solucaoTI = new SolucaoTI();
	private String tags;
	
	@Inject
	private SolucaoTIBC bc;


	@SuppressWarnings("serial")
	public InsereSolucaoTI() {
		
		//PAINEL COM AS MENSAGENS DE VALIDAÇÃO DO FORMULÁRIO
		add(feedback.setVisible(false)); 
		
     	Form<Object> form = new Form<Object>("form");
    	form.add(new TextField<String>("titulo", new PropertyModel<String>(this,"solucaoTI.titulo")));
    	
    	final TextField<String> versao = new TextField<String>("versao", new PropertyModel<String>(this,"solucaoTI.versao"));
    	versao.setRequired(true);
    	
    	final WebMarkupContainer containerVersao = new WebMarkupContainer("container_versao");
    	containerVersao.setOutputMarkupId(true); //habilita ajax
    	 	
    	final DropDownChoice<String> choice = new DropDownChoice<String>("categoria", new PropertyModel<String>(this, "solucaoTI.categoria"), RecursoTI.valores());
    	choice.setRequired(true);
    	
    	form.add(choice);
    	
    	choice.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			private static final long serialVersionUID = 1L;

			@Override
            protected void onUpdate(AjaxRequestTarget target) {
				if (choice.getValue().equals("0")) {
					containerVersao.add(new AttributeModifier("class", new Model<String>("visivel")));
					versao.setRequired(true);
				} else {
					containerVersao.add(new AttributeModifier("class", new Model<String>("hidden")));
					versao.setRequired(false);
				}	
				target.addComponent(containerVersao);
				
            }
        });
    	
    	
    	
    	
    	form.add(new TextField<String>("nome", new PropertyModel<String>(this,"solucaoTI.nome")).setRequired(true));
    	form.add(containerVersao);
		containerVersao.add(versao);
    	form.add(new TextArea<String>("descricao", new PropertyModel<String>(this, "solucaoTI.descricao")).setRequired(true));
    	form.add(new TextArea<String>("solucao", new PropertyModel<String>(this, "solucaoTI.solucao")).setRequired(true));
    	form.add(new TextField<String>("tags", new PropertyModel<String>(this, "tags")));	
    	
    	form.add(new Button("cadastra") {
    		@Override
    		public void onSubmit() {
    			super.onSubmit();
       			try {
       				bc.salvaSolucao(solucaoTI, tags);
      				setResponsePage(InsereSolucaoTI.class);
      				feedback.setVisible(true);
    				getSession().info("Cadastro salvo com sucesso!"); //TODO Fazer aparecer o cadastro salvo quando já fez o redirect
  
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


	public void setTags(String tags) {
		this.tags = tags;
	}


	public String getTags() {
		return tags;
	}
}
