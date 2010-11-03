package br.org.universa.web;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import br.org.universa.negocio.CadastroBase;
import br.org.universa.persistencia.CadastroBaseDAO;

public class CadastroBaseDataProvider implements IDataProvider<CadastroBase> {

	private static final long serialVersionUID = 1L;

	private CadastroBaseDAO dao = new CadastroBaseDAO();

	public Iterator<CadastroBase> iterator(int first, int count) {
		return dao.recuperaTodos().iterator();
	}

	public IModel<CadastroBase> model(CadastroBase cadastro) {
		return new DetachableCadastroModel(cadastro);
	}

	public int size() {
		return dao.recuperaTodos().size();
	}

	public void detach() {
		// TODO Auto-generated method stub
		
	}


}
