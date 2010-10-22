package br.org.universa.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class PaginaFluxoInicial extends WebPage {
	
	public PaginaFluxoInicial() {
		add(new BookmarkablePageLink<Object>("cadastro", CadastroBasePage.class));
		add(new BookmarkablePageLink<Object>("lista", ListaBaseConhecimento.class));
	}

}
