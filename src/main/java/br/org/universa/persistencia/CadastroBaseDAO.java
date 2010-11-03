package br.org.universa.persistencia;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Query;

import br.org.universa.negocio.CadastroBase;

public class CadastroBaseDAO extends DAOBase<CadastroBase> {
	
	public CadastroBaseDAO() {
		super(CadastroBase.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<CadastroBase> recuperaTodos() {
		Collection<CadastroBase> cadastros = null;
		try {
			Query query = getSession().createQuery("select from " + clazz.getName());
			cadastros = new ArrayList<CadastroBase>(query.getResultList());
		} finally {
			getSession().close();
		}
		return cadastros;
	}

}
