package br.org.universa.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class ListaBaseConhecimento extends WebPage {
	
	
	public ListaBaseConhecimento() {
		add(new Link<Object>("cancela") {
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
            	setResponsePage(Menu.class);
            }
        });
	}
}
