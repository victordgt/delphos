package br.org.universa.persistencia;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.universa.negocio.CadastroBase;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class CadastroBaseDAO extends DAOBase<CadastroBase> {
	
    private static final Logger log = LoggerFactory.getLogger(CadastroBaseDAO.class);
	
    @Inject
	public CadastroBaseDAO(Provider<EntityManager> emProvider) {		
		super(CadastroBase.class, emProvider);
		log.debug("Cria o dao de CadastroBase");
	}


}
