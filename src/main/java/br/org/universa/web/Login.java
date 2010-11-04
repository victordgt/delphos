package br.org.universa.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class Login extends WebPage {

  private TextField<String> userIdField;
  private PasswordTextField passField;
  private Form<Object> form;

  public Login(){
    userIdField = new TextField<String>("userId", new Model<String>(""));
    passField = new PasswordTextField("password",new Model<String>(""));

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
		setResponsePage(Menu.class);
	}
}


protected String getUserId() {
  return userIdField.getModelObject();
}

protected String getPassword() {
    return passField.getModelObject();
  }
}