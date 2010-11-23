package br.org.universa.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.Usuario;
import br.org.universa.persistencia.UsuarioDAO;

public class Login extends WebPage {

  private TextField<String> userIdField;
  private PasswordTextField passField;
  private Form<Object> form;
  private final FeedbackPanel feedback = new FeedbackPanel("feedback");
  
  private final Usuario usuario = new Usuario();

  public Login(){
	add(feedback.setVisible(false));  
	  
    userIdField = new RequiredTextField<String>("userId", new PropertyModel<String>(usuario, "login"));
    passField = new PasswordTextField("password", new PropertyModel<String>(usuario, "senha"));

    //assegura que o password continuará preenchido após uma nova renderização da página
    passField.setResetPassword(false);

    form = new LoginForm("loginForm");
    form.add(userIdField);
    form.add(passField);
    add(form);
  }

class LoginForm extends Form<Object> {

	private static final long serialVersionUID = 1L;
	public LoginForm(String id) {
	    super(id);
	}
	
	@Override
	public void onSubmit() {
		super.onSubmit();
		UsuarioDAO dao = new UsuarioDAO();

		//dao.salvaOuAltera(usuario); //para cadastrar um usuário do banco
		Usuario usuarioRecuperado = dao.recuperaPorLogin(getUserId());
		
		if (usuarioRecuperado != null && getPassword().equals(usuarioRecuperado.getSenha())) {
			setResponsePage(Menu.class);
		} else {
			feedback.setVisible(true);
			info("Usuario ou senha inválidos");
		}
	}
	
	@Override
	protected void onError() {
		super.onError();
		feedback.setVisible(true);
	}
	
	
}


protected String getUserId() {
  return userIdField.getModelObject();
}

protected String getPassword() {
    return passField.getModelObject();
  }
}