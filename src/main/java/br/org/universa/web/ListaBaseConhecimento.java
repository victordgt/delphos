package br.org.universa.web;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;

import br.org.universa.negocio.CadastroBase;

public class ListaBaseConhecimento extends WebPage {
	
	
	public ListaBaseConhecimento() {
	    add(new DataView<CadastroBase>("lista", new CadastroBaseDataProvider())
	            {
	                @Override
	                protected void populateItem(final Item<CadastroBase> item)
	                {
	                    CadastroBase cadastro = item.getModelObject();
	                    item.add(new Label("nome", cadastro.getNome()));
	                    item.add(new Label("categoria", cadastro.getCategoria()));
	                    item.add(new Label("versao", cadastro.getVersao()));
	                    item.add(new Label("descricao", cadastro.getDescricao()));
	                  

	                    item.add(new AttributeModifier("class", true, new AbstractReadOnlyModel<String>()
	                    {
	                        @Override
	                        public String getObject()
	                        {
	                            return (item.getIndex() % 2 == 1) ? "even" : "odd";
	                        }
	                    }));
	                }
	            });

		
		
		add(new Link<Object>("cancela") {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
            	setResponsePage(Menu.class);
            }
        });
	}
}
