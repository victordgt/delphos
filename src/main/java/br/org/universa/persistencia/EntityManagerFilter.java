package br.org.universa.persistencia;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

public class EntityManagerFilter implements Filter {
    private static final Logger logger = Logger.getLogger(EntityManagerFilter.class.getName());

    private static final ThreadLocal<EntityManager> em = new ThreadLocal<EntityManager>();

    private EntityManagerFactory emf;

    public void init(FilterConfig filterConfig) throws ServletException
    {
        logger.info("Criando um EntityManagerFactory");
        emf = EMF.get();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            em.set(emf.createEntityManager());
            chain.doFilter(request, response);
            
        }
        finally {
            em.get().close();
        }
    }

    public void destroy() {
        logger.info("Fecha a EntityManagerFactory");
        emf.close();
    }

    /**
     * This module binds the JDO {@link javax.jdo.PersistenceManager} interface to the provider that allows the
     * implementation to be injected as Provider&lt;PersistenceManager&gt;.
     */
    public static class GuiceModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(EntityManager.class).toProvider(new Provider<EntityManager>() {
                public EntityManager get() {
                    return EntityManagerFilter.em.get();
                }
            });
        }
    }
}
