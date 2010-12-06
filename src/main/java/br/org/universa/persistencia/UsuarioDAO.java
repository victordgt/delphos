package br.org.universa.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.universa.negocio.Usuario;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class UsuarioDAO extends DAOBase<Usuario> {
	
    private static final Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
	
    @Inject
	public UsuarioDAO(Provider<EntityManager> emProvider) {
		super(Usuario.class, emProvider);
	}


    @Override
	public Usuario recuperaPorValorAtributo(Object valorAtributo) {
		Query query = getEntityManager().createQuery(getQueryValorAtributos());
		query.setParameter("login", valorAtributo + "%");
		Object usuario = null;
		
		try {
			usuario = query.getSingleResult();
		} catch(NoResultException ex)  {
			return null;
		}
		return (Usuario)usuario;
	}
	
    @Override
	protected String getQueryValorAtributos() {
		return "select usuario from " + clazz.getName() + " usuario where usuario.login like :login";
	}

}
