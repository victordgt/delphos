package br.org.universa.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {
	
	private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
	private EntityManager em;
	
	public EntityManager getSession() {
		if (em == null) {
			em = emfInstance.createEntityManager();
		}
		return em;
	}
	
	public DAO() {
		
	}

}
