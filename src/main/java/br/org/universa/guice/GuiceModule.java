package br.org.universa.guice;

import br.org.universa.negocio.SolucaoTI;
import br.org.universa.negocio.Tag;
import br.org.universa.negocio.Usuario;
import br.org.universa.negocio.controller.SolucaoTIBC;
import br.org.universa.negocio.controller.UsuarioBC;
import br.org.universa.persistencia.DAO;
import br.org.universa.persistencia.EntityManagerFilter;
import br.org.universa.persistencia.SolucaoTIDAO;
import br.org.universa.persistencia.TagDAO;
import br.org.universa.persistencia.UsuarioDAO;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

/**
 * This Guice module sets up the bindings used in this Wicket application, including the
 * JDO PersistenceManager.
 */
public class GuiceModule extends AbstractModule
{
    @Override
    protected void configure() {
         //Enable per-request-thread EntityManager injection.
        install(new EntityManagerFilter.GuiceModule());

        // DAO`s
        bind(new TypeLiteral<DAO<SolucaoTI>>() { }).to(SolucaoTIDAO.class);
        bind(new TypeLiteral<DAO<Usuario>>() { }).to(UsuarioDAO.class);
        bind(new TypeLiteral<DAO<Tag>>() { }).to(TagDAO.class);
        
        bind(SolucaoTIBC.class);
            
        // Business Controllers
        bind(UsuarioBC.class);
        bind(SolucaoTI.class);
        
    }
}
