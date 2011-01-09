package br.org.universa.persistencia;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.universa.negocio.SolucaoTI;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class SolucaoTIDAO extends DAOBase<SolucaoTI> {
	
    private static final Logger log = LoggerFactory.getLogger(SolucaoTIDAO.class);
	
    @Inject
	public SolucaoTIDAO(Provider<EntityManager> emProvider) {		
		super(SolucaoTI.class, emProvider);
		log.debug("Cria o dao de CadastroBase");
	}


}
