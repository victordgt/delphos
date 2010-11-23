package br.org.universa.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.Usuario;
import br.org.universa.persistencia.UsuarioDAO;

public class Login extends WebPage {

  private TextField<String> userIdField;
  private PasswordTextField passField;
  private Form<Object> form;
  
  private final Usuario usuario = new Usuario();

  public Login(){
	  
	  
    userIdField = new TextField<String>("userId", new PropertyModel(usuario, "login"));
    passField = new PasswordTextField("password", new PropertyModel(usuario, "senha"));

    //assegura que o password continuará preenchido após uma nova renderização da página
    passField.setResetPassword(false);

    form = new LoginForm("loginForm");
    form.add(userIdField);
    form.add(passField);
    add(form);
  }

// Define your LoginForm and override onSubmit
class LoginForm extends Form<Object> {

	private static final long serialVersionUID = 1L;
	public LoginForm(String id) {
	    super(id);
	}
	
	@Override
	public void onSubmit() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuarioRecuperado = dao.recuperaPorLogin(getUserId());
		
		if (usuarioRecuperado != null && getPassword().equals(usuarioRecuperado.getSenha())) {
			setResponsePage(Menu.class);
		} else {
			info("Usuario ou senha inválidos");
		}
	}
}


protected String getUserId() {
  return userIdField.getModelObject();
}

protected String getPassword() {
    return passField.getModelObject();
  }
}