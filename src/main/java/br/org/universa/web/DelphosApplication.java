package br.org.universa.web;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see br.org.universa.Start#main(String[])
 */
public class DelphosApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public DelphosApplication()
	{
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<CadastroBasePage> getHomePage()
	{
		return CadastroBasePage.class;
	}

}
