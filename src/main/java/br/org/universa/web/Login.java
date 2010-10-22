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
    userIdField = new TextField<String>("userId", new Model(""));
    passField = new PasswordTextField("password",new Model(""));

    //assegura que o password continuará preenchido após uma nova renderização da página
    passField.setResetPassword(false);

    form = new LoginForm("loginForm");
    form.add(userIdField);
    form.add(passField);
    add(form);
  }

// Define your LoginForm and override onSubmit
class LoginForm extends Form {
  public LoginForm(String id) {
    super(id);
  }
  @Override
  public void onSubmit() {
    String userId = Login.this.getUserId();
    String password = Login.this.getPassword();
    System.out.println("You entered User id "+ userId +
               " and Password " + password);
    setResponsePage(PaginaFluxoInicial.class);
  }
}

/** Helper methods to retrieve the userId and the password **/

protected String getUserId() {
  return userIdField.getModelObject();
}

protected String getPassword() {
    return passField.getModelObject();
  }
}