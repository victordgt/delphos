package br.org.universa.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import br.org.universa.negocio.Entidade;

public abstract class DAOBase<T extends Entidade> {
	 
	private EntityManager em;
	protected Class<T> clazz;
	
	public DAOBase(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	protected EntityManager getSession() {
		if (em == null || !em.isOpen()) {
			em = EMF.get().createEntityManager();
		}
	
		return em;
	}

	public void exclui(T entidade) {
		try {

			getSession().getTransaction().begin();
			
			//como o objeto dado como parâmetro pode estar detached, então é necessário recuperar um objeto que esteja em uma sessão.
			T entidadeManaged = getSession().find(clazz, entidade.getId());
			getSession().remove(entidadeManaged);
			getSession().getTransaction().commit();

		} finally {
			getSession().close();
		}
	}

	public T recupera(Long chave) {
		T entidade = null;
		try {
			entidade = getSession().find(clazz, chave);
		} finally {
			getSession().close();
		}
		return entidade;
	}
	
	public abstract ArrayList<T> recuperar(boolean todos, int maximoResultados, int primeiroResultado);
	
	public ArrayList<T> recuperaTodos() {
		//Recupera todos, os parametros com zero não fazem diferença
		return recuperar(true, 0, 0);
	}

	public void salvaOuAltera(T entidade) {
		try {
			getSession().getTransaction().begin();
			getSession().persist(entidade);
			getSession().getTransaction().commit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erro ao salvar entidade");
		}	finally {
			getSession().close();
		}
		
	}

}
