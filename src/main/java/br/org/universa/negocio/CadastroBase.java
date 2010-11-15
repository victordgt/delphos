package br.org.universa.negocio;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity(name="br.org.universa.negocio.CadastroBase")
@Table(name="CADASTRO_BASE")
public class CadastroBase extends Entidade {
	
	private static final long serialVersionUID = 1L;
	

	private String titulo;
	private String categoria;
	private String nome;
	private String versao;
	private String descricao;
	private String solucao;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getVersao() {
		return versao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public String getSolucao() {
		return solucao;
	}
}
