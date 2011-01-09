package br.org.universa.negocio.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.universa.negocio.SolucaoTI;
import br.org.universa.negocio.Tag;
import br.org.universa.persistencia.DAO;

import com.google.inject.Inject;

public class SolucaoTIBC {
	
	@Inject
	DAO<SolucaoTI> solucaoTIDAO;
	
	@Inject
	DAO<Tag> tagDAO;
	
	Logger log = LoggerFactory.getLogger(SolucaoTIBC.class);

	public void salvaSolucao(SolucaoTI solucao, String tags) {
		Set<Tag> tagsSet = new HashSet<Tag>(); 
		
		for (String descricao : tags.split(" ")) {
			Tag tag = null;
			tag = new Tag();
			tag.setDescricao(descricao);
			tagsSet.add(tag);
			
		}
		
		solucao.setTags(tagsSet);
		solucaoTIDAO.salvaOuAltera(solucao);
	
	}
	
	public List<SolucaoTI> listaSolucaoPorTitulo(String titulo) {
		System.out.println("LISTA POR TITULO");
		return solucaoTIDAO.recuperaTodos();
		
	}
	
	public List<SolucaoTI> listaSolucaoPorTag(String tag) {
		System.out.println("LISTA POR TAG");
		return solucaoTIDAO.recuperaTodos();
	}

	public void exclui(SolucaoTI solucao) {
		solucaoTIDAO.exclui(solucao);
	}
	
}
