package br.org.universa.web;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.ISessionStore;

import br.org.universa.guice.GuiceModule;

public class DelphosApplication extends WebApplication {

	
	public Class<Login> getHomePage()
    {
        return Login.class;
    }	
	

    @Override
    protected void init()
    {
        super.init();

        // configuração padrãop para o Google App Engine
        getResourceSettings().setResourcePollFrequency(null);

        // Enable Guice for field injection on Wicket pages.  Unfortunately, constructor injection into
        // pages is not supported.  Supplying ServletModule is optional; it enables usage of @RequestScoped and
        // @SessionScoped, which may not be useful for Wicket applications because the WebPage instances are
        // already stored in session, with their dependencies injected once per session.
        addComponentInstantiationListener(new GuiceComponentInjector(this, new GuiceModule()));
//        addComponentInstantiationListener(new GuiceComponentInjector(this, new ServletModule(), new GuiceModule()));
    }

    @Override
    protected ISessionStore newSessionStore()
    {
        // for Google App Engine
        return new HttpSessionStore(this);
    }
}
