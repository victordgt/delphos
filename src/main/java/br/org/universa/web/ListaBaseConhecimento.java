package br.org.universa.web;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import br.org.universa.negocio.CadastroBase;
import br.org.universa.persistencia.CadastroBaseDAO;

public class ListaBaseConhecimento extends WebPage {


	public ListaBaseConhecimento() {

		List<CadastroBase> cadastros = (List<CadastroBase>)new CadastroBaseDAO().recuperaTodos();
	    DataView<CadastroBase> dataView = new DataView<CadastroBase>("lista", new ListDataProvider<CadastroBase>(cadastros), 5) {

			private static final long serialVersionUID = 1L;

			public void populateItem(final Item<CadastroBase> item) {
              CadastroBase cadastro = item.getModelObject();
              item.add(new Label("nome", cadastro.getNome()));
              item.add(new Label("categoria", cadastro.getCategoria()));
              item.add(new Label("versao", cadastro.getVersao()));
              item.add(new Label("descricao", cadastro.getDescricao()));
              item.add(remover(item));
              item.add(editar(item));
            }
			
		private Component editar(Item<CadastroBase> item) {
			final CadastroBase cadastro = item.getModelObject();
			
			Link<Object> link = new Link<Object>("editar") {

				private static final long serialVersionUID = 1L;

				public void onClick() {
				
					setResponsePage(new EditaCadastroBasePage(cadastro));
				}

			};				
			

			return link;
		}

		public Link<Object> remover(Item<CadastroBase> item) { 
				
				final CadastroBase cadastro = item.getModelObject();
			
				
				Link<Object> link = new Link<Object>("remover") {

					private static final long serialVersionUID = 1L;

					public void onClick() {
						CadastroBaseDAO cadastroDao = new CadastroBaseDAO();
						cadastroDao.exclui(cadastro);
						setResponsePage(ListaBaseConhecimento.class);
					}

				};				
				

				return link;
			}			
        };
		add(dataView);
		add(new PagingNavigator("navigator", dataView));	 
	    
	    

		add(new Link<Object>("cancela") {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
            	setResponsePage(Menu.class);
            }
        });
	}
}
