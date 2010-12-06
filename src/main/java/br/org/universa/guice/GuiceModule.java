package br.org.universa.guice;

import br.org.universa.negocio.CadastroBase;
import br.org.universa.negocio.Usuario;
import br.org.universa.persistencia.CadastroBaseDAO;
import br.org.universa.persistencia.DAO;
import br.org.universa.persistencia.EntityManagerFilter;
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

        // Business object bindings go here.
        bind(new TypeLiteral<DAO<CadastroBase>>() { }).to(CadastroBaseDAO.class);
        bind(new TypeLiteral<DAO<Usuario>>() { }).to(UsuarioDAO.class);
        
    }
}
