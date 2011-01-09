package br.org.universa.web;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class BasePage extends WebPage {
	public BasePage() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
			
		if (user == null) {
			WebRequest request = (WebRequest)RequestCycle.get().getRequest();
			String url = request.getHttpServletRequest().getRequestURI();
			RequestCycle.get().setRequestTarget(new RedirectRequestTarget(userService.createLoginURL(url)));		
		}
	}
}
