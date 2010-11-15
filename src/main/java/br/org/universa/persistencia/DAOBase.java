package br.org.universa.persistencia;

import java.util.Collection;

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
			T entidade2 = getSession().find(clazz, entidade.getId());
			
			getSession().remove(entidade2);
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
