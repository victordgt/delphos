package br.org.universa.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.universa.negocio.Tag;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class TagDAO extends DAOBase<Tag> {
	
	private Logger log = LoggerFactory.getLogger(TagDAO.class);
	
	@Inject
	protected TagDAO(Provider<EntityManager> emProvider) {
		super(Tag.class, emProvider);
	}
	
	   @Override
		public Tag recuperaPorValorAtributo(Object valorAtributo) {
	    	log.info("Recupera por descricao a tag: " + valorAtributo);
			Query query = getEntityManager().createQuery(getQueryValorAtributos());
			query.setParameter("descricao", valorAtributo + "%");
			Object tag = null;
			
			try {
				tag = query.getSingleResult();
			} catch(NoResultException ex)  {
				return null;
			}
			return (Tag)tag;
		}
		
	    @Override
		protected String getQueryValorAtributos() {
			return "select tag from " + clazz.getName() + " tag where tag.descricao like :descricao";
		}
	

}
