package br.org.universa.persistencia;

import java.util.ArrayList;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.universa.negocio.CadastroBase;

public class CadastroBaseDAO extends DAOBase<CadastroBase> {
	
    private static final Logger log = LoggerFactory.getLogger(CadastroBaseDAO.class);
	
	public CadastroBaseDAO() {
		super(CadastroBase.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<CadastroBase> recuperar(boolean todos, int maximoResultados, int primeiroResultado){
		ArrayList<CadastroBase> cadastros = null;
		try {
			log.info("LISTA COM CADASTROS BASE");
			cadastros = new ArrayList<CadastroBase>();
			Query query = getSession().createQuery("select from " + clazz.getName());
			
			if (!todos) {
				query.setFirstResult(primeiroResultado);
				query.setMaxResults(maximoResultados);
			}

			cadastros.addAll(query.getResultList());
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			getSession().close();
		}
		return cadastros;
	}

}
