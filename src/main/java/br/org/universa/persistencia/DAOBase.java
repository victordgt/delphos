package br.org.universa.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.org.universa.negocio.Entidade;

import com.google.inject.Provider;

public abstract class DAOBase<T extends Entidade> implements DAO<T> {

	private final Provider<EntityManager> emProvider;
	protected final Class<T> clazz;

	protected DAOBase(Class<T> clazz, Provider<EntityManager> emProvider) {
		this.clazz = clazz;
		this.emProvider = emProvider;
	}

	public EntityManager getEntityManager() {
		return emProvider.get();
	}

	@Override
	public void exclui(T entidade) {
		// como o objeto dado como parâmetro pode estar detached, então é
		// necessário recuperar um objeto que esteja em uma sessão.
		T entidadeManaged = getEntityManager().find(clazz, entidade.getId());
		getEntityManager().remove(entidadeManaged);
	}

	@Override
	public T recupera(Long chave) {
		T entidade = null;
		entidade = getEntityManager().find(clazz, chave);
		return entidade;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> recuperaTodos() {
		List<T> entidades = null;
		entidades = new ArrayList<T>();
		Query query = getEntityManager().createQuery(
				"select from " + clazz.getName());
		entidades.addAll(query.getResultList());
		return entidades;
	}

	@Override
	public void salvaOuAltera(T entidade) {
		getEntityManager().persist(entidade);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T recuperaPorValorAtributo(Object valorAtributo) {
		Query query = getEntityManager().createQuery(getQueryValorAtributos());
		T entidade = (T) query.getSingleResult();
		return entidade;
	}

	protected String getQueryValorAtributos() {
		return "select usuario from " + clazz.getName();
	}

	
	public void runInTransaction(Runnable block) {
		EntityTransaction tx = emProvider.get().getTransaction();
		try {
			tx.begin();
			block.run();
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	

}
