package br.org.universa.web;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.CadastroBase;
import br.org.universa.persistencia.CadastroBaseDAO;

public class ListaBaseConhecimento extends WebPage {
	
	
	private List<CadastroBase> cadastros;
	
	private CadastroBaseDAO cadastroDao = new CadastroBaseDAO();

	public ListaBaseConhecimento() {
		
		cadastros =  (List<CadastroBase>)cadastroDao.recuperaTodos();
		
		/*
	    add(new DataView<CadastroBase>("lista", new ListDataProvider<CadastroBase>(cadastros)) {

			private static final long serialVersionUID = 1L;

			public void populateItem(final Item<CadastroBase> item) {
              CadastroBase cadastro = item.getModelObject();
              item.add(new Label("nome", cadastro.getNome()));
              item.add(new Label("categoria", cadastro.getCategoria()));
              item.add(new Label("versao", cadastro.getVersao()));
              item.add(new Label("descricao", cadastro.getDescricao()));
            }
        });
        */
	    
	    final PageableListView<CadastroBase> listView = new PageableListView<CadastroBase>("lista", new PropertyModel<List<CadastroBase>>(this,
            "cadastros"), 5) {

			private static final long serialVersionUID = 1L;

			@Override
            public void populateItem(final ListItem<CadastroBase> item) {
                final CadastroBase cadastro = item.getModelObject();
                
                item.add(new Label("nome", cadastro.getNome()));
                item.add(new Label("categoria", cadastro.getCategoria()));
                item.add(new Label("versao", cadastro.getVersao()));
                item.add(new Label("descricao", cadastro.getDescricao()));
                item.add(remover(item));
                //item.add(EditBook.link("edit", book.getId())); TODO Fazer a edição do CadastroBase
      
            }
			

			public Link<Object> remover(ListItem<CadastroBase> item) { 
				
				final CadastroBase cadastro = item.getModelObject();
			
				
				Link<Object> link = new Link<Object>("remover") {

					private static final long serialVersionUID = 1L;

					public void onClick() {
						
						CadastroBase cadastro2 = cadastroDao.recupera(cadastro.getId());
						
						System.out.println("nome cadastro: " + cadastro2.getNome());
						System.out.println("id cadastro: " + cadastro2.getId());
						cadastroDao.exclui(cadastro2);
						setResponsePage(ListaBaseConhecimento.class);
					}

				};				
				

				return link;
			}
	
			
        };
        add(listView);
        add(new PagingNavigator("navigator", listView));	    
	    
		add(new Link<Object>("cancela") {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
            	setResponsePage(Menu.class);
            }
        });
	}

	public void setCadastros(List<CadastroBase> cadastros) {
		this.cadastros = cadastros;
	}

	public List<CadastroBase> getCadastros() {
		return cadastros;
	}
}
