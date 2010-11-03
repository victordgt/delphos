package br.org.universa.persistencia;

import java.util.Collection;

import javax.persistence.EntityManager;

public abstract class DAOBase<T> {
	 
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
			getSession().remove(entidade);
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

	public abstract Collection<T> recuperaTodos();

	public void salvaOuAltera(T entidade) {
		try {
			getSession().getTransaction().begin();
			getSession().persist(entidade);
			getSession().getTransaction().commit();
		} finally {
			getSession().close();
		}
	}

}
