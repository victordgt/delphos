package br.org.universa.web;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class Menu extends BasePage {
	
	public Menu() {
		add(new BookmarkablePageLink<Object>("cadastro", InsereSolucaoTI.class));
		add(new BookmarkablePageLink<Object>("pesquisa", Pesquisa.class));
	}

}
