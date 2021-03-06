package br.org.universa.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import br.org.universa.negocio.Usuario;
import br.org.universa.negocio.controller.UsuarioBC;

import com.google.inject.Inject;

public class Login extends BasePage {

	private TextField<String> userIdField;
	private PasswordTextField passField;
	private Form<Object> form;
	private final FeedbackPanel feedback = new FeedbackPanel("feedback");

	@Inject
	private UsuarioBC usuarioBC;

	private final Usuario usuario = new Usuario();

	public Login() {
		add(feedback.setVisible(false));

		userIdField = new RequiredTextField<String>("userId",
				new PropertyModel<String>(usuario, "login"));
		passField = new PasswordTextField("password",
				new PropertyModel<String>(usuario, "senha"));

		// assegura que o password continuar� preenchido ap�s uma nova
		// renderiza��o da p�gina
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

			boolean autenticado = usuarioBC.login(getUserId(), getPassword());

			if (autenticado) {
				setResponsePage(Menu.class);
			} else {
				feedback.setVisible(true);
				info("Usuario ou senha inv�lidos");
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