package br.org.universa.negocio.controller;

import br.org.universa.negocio.Usuario;
import br.org.universa.persistencia.DAO;

import com.google.inject.Inject;

public class UsuarioBC {
	
	@Inject
	private DAO<Usuario> dao;
	
	public boolean login(String login, String senha) {
		Usuario usuarioRecuperado = dao.recuperaPorValorAtributo(login);
		
		if (usuarioRecuperado == null) {
			Usuario usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setSenha(senha);			
			dao.salvaOuAltera(usuario);
			
			return true;
		} else if (usuarioRecuperado.getSenha().equals(senha))  {
			return true;
		}
		
		return false;
	} 

}
