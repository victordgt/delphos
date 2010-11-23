package br.org.universa.negocio;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="br.org.universa.negocio.Usuario")
@Table(name="USUARIO")
public class Usuario extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


}
