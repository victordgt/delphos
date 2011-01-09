package br.org.universa.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.SolucaoTI;
import br.org.universa.negocio.controller.SolucaoTIBC;

import com.google.inject.Inject;

public class Pesquisa extends BasePage {

	@Inject
	private SolucaoTIBC bc;
	
	private String titulo;
	private String tag;
	
	private TextField<String> tituloTextField;
	private TextField<String> tagTextField;

	private WebMarkupContainer container;

	private SolucaoDataProvider dataProvider;

	private DataView<SolucaoTI> dataview;

	private AjaxPagingNavigator pagingNavigator;	
	
	
	private final class SolucoesDataView extends DataView<SolucaoTI> {

		private static final long serialVersionUID = -5125713210078125849L;

		private SolucoesDataView(String id,
				IDataProvider<SolucaoTI> dataProvider, int itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		public void populateItem(final Item<SolucaoTI> item) {
			SolucaoTI cadastro = item.getModelObject();
			item.add(new Label("nome", cadastro.getNome()));
			item.add(new Label("categoria", cadastro.getCategoria()));
			item.add(new Label("versao", cadastro.getVersao()));
			item.add(new Label("descricao", cadastro.getDescricao()));
			item.add(remover(item));
			item.add(editar(item));
		}

		private Component editar(Item<SolucaoTI> item) {
			final SolucaoTI cadastro = item.getModelObject();

			Link<Object> link = new Link<Object>("editar") {

				private static final long serialVersionUID = 1L;

				public void onClick() {

					setResponsePage(new EditaSolucaoTI(cadastro, Pesquisa.this.pagingNavigator.getPage()));
				}

			};

			return link;
		}

		public Link<Object> remover(Item<SolucaoTI> item) {

			final SolucaoTI cadastro = item.getModelObject();

			Link<Object> link = new Link<Object>("remover") {

				private static final long serialVersionUID = 1L;

				public void onClick() {
				
					bc.exclui(cadastro);
					setResponsePage(Pesquisa.this.pagingNavigator.getPage());
				}

			};

			return link;
		}
	}


	
	public Pesquisa() {
		
		Form<Object> form  = new Form<Object>("pesquisa");
		tituloTextField = new TextField<String>("titulo", new PropertyModel<String>(this, "titulo"));
		form.add(tituloTextField);
		
		form.add(new AjaxButton("botao_pesquisa_titulo"){
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				String titulo = Pesquisa.this.tituloTextField.getModelObject();
				
				List<SolucaoTI> solucoes = bc.listaSolucaoPorTitulo(titulo);
				dataProvider.setSolucoes(solucoes);
				target.addComponent(container);
			}
			
		});
		
		tagTextField = new TextField<String>("tag", new PropertyModel<String>(this, "tag")); 
		form.add(tagTextField);
		
		
		
		form.add(new AjaxButton("botao_pesquisa_tag"){
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form formTag) {
				String tag = Pesquisa.this.tituloTextField.getModelObject();
				
				List<SolucaoTI> solucoes = bc.listaSolucaoPorTitulo(tag);
				
				dataProvider.setSolucoes(solucoes);
				
				target.addComponent(container);
			}
			
		});
		
		form.setOutputMarkupId(true);
		
		List<SolucaoTI> solucoes = new ArrayList<SolucaoTI>();
		container = getLista(solucoes);
		container.setOutputMarkupId(true);
				
		add(form);
		add(container);

		
		add(new BookmarkablePageLink<Object>("menu", Menu.class));
		
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}
	
	private WebMarkupContainer getLista(List<SolucaoTI> solucoes) {
		
		final WebMarkupContainer container = new WebMarkupContainer(
				"container");
		dataProvider = new SolucaoDataProvider(new ArrayList<SolucaoTI>());
		
		dataview = new SolucoesDataView("lista", dataProvider, 5);
		
		container.add(dataview);
		dataview.setOutputMarkupId(true);
		

		pagingNavigator = new AjaxPagingNavigator(
				"navigator", dataview) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onAjaxEvent(AjaxRequestTarget target) {
				super.onAjaxEvent(target);
				
				target.addComponent(container);
			}
			
		};

		pagingNavigator.setOutputMarkupId(true);
		container.add(pagingNavigator);	
		container.setOutputMarkupId(true);
		
		return container;
	}

	

}
