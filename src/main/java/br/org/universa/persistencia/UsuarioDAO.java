package br.org.universa.persistencia;

import java.util.ArrayList;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.universa.negocio.CadastroBase;
import br.org.universa.negocio.Usuario;

public class UsuarioDAO extends DAOBase<Usuario> {
	
    private static final Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
	
	public UsuarioDAO() {
		super(Usuario.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> recuperar(boolean todos, int maximoResultados, int primeiroResultado){
		ArrayList<Usuario> usuarios = null;
		try {
			log.info("LISTA COM CADASTROS BASE");
			usuarios = new ArrayList<Usuario>();
			Query query = getSession().createQuery("select from " + clazz.getName());
			
			if (!todos) {
				query.setFirstResult(primeiroResultado);
				query.setMaxResults(maximoResultados);
			}

			usuarios.addAll(query.getResultList());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			getSession().close();
		}
		return usuarios;
	}

	public Usuario recuperaPorLogin(String userId) {
		Query query = getSession().createQuery("select from " + clazz.getName() + " where login :=login");
		query.setParameter("login", userId);
		return (Usuario)query.getSingleResult();	
	}

}
