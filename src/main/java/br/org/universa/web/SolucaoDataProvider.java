package br.org.universa.web;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import br.org.universa.negocio.SolucaoTI;

public class SolucaoDataProvider implements IDataProvider<SolucaoTI> {
	
	private static final long serialVersionUID = 7816571856668845135L;
	private List<SolucaoTI>  solucoes;
	
	public List<SolucaoTI> getSolucoes() {
		return solucoes;
	}

	public void setSolucoes(List<SolucaoTI> solucoes) {
		this.solucoes = solucoes;
	}	
	

	public SolucaoDataProvider(List<SolucaoTI> solucoes) {
		this.solucoes = solucoes;
	
	}

	@Override
	public Iterator<SolucaoTI> iterator(int first, int count) {
		int toIndex = first + count;
		if (toIndex > solucoes.size())
		{
			toIndex = solucoes.size();
		}
		return solucoes.subList(first, toIndex).listIterator();	
	}

	@Override
	public IModel<SolucaoTI> model(SolucaoTI solucao) {
		return new Model<SolucaoTI>(solucao);
	}

	@Override
	public int size() {
		return solucoes.size();
	}

	@Override
	public void detach() {
		
	}
}
