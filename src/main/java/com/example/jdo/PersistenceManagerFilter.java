package com.example.jdo;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class PersistenceManagerFilter implements Filter
{
    private static final Logger logger = Logger.getLogger(PersistenceManagerFilter.class.getName());

    private static final ThreadLocal<PersistenceManager> pm = new ThreadLocal<PersistenceManager>();

    private PersistenceManagerFactory pmf;

    public void init(FilterConfig filterConfig) throws ServletException
    {
        logger.info("Creating PersistenceManagerFactory");
        pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        try
        {
            pm.set(pmf.getPersistenceManager());
            chain.doFilter(request, response);
        }
        finally
        {
            pm.get().close();
        }
    }

    public void destroy()
    {
        logger.info("Closing PersistenceManagerFactory");
        pmf.close();
    }

    /**
     * This module binds the JDO {@link javax.jdo.PersistenceManager} interface to the provider that allows the
     * implementation to be injected as Provider&lt;PersistenceManager&gt;.
     */
    public static class GuiceModule extends AbstractModule
    {
        @Override
        protected void configure()
        {
            bind(PersistenceManager.class).toProvider(new Provider<PersistenceManager>()
            {
                public PersistenceManager get()
                {
                    return PersistenceManagerFilter.pm.get();
                }
            });
        }
    }
}
