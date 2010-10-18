package br.org.universa.negocio;

import java.io.Serializable;

public class CadastroBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private String categoria;
	private String nome;
	private String versao;

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
}
