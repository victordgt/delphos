package br.org.universa.negocio;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TAG")
public class Tag extends Entidade {
	
	private static final long serialVersionUID = 1L;
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SolucaoTI solucaoTI;
		
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setSolucaoTI(SolucaoTI solucaoTI) {
		this.solucaoTI = solucaoTI;
	}
	public SolucaoTI getSolucaoTI() {
		return solucaoTI;
	}
}
