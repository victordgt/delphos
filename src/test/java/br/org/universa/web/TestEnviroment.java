package br.org.universa.web;
import java.util.Map;

import com.google.apphosting.api.ApiProxy;

class TestEnvironment implements ApiProxy.Environment {
    public String getAppId() {
        return "Unit Tests";
    }

    public String getVersionId() {
        return "1.0";
    }

    public void setDefaultNamespace(String s) { }

    public String getRequestNamespace() {
        return null;
    }

    public String getDefaultNamespace() { 
        return null;
    }

    public String getAuthDomain() {
      return null;
    }

    public boolean isLoggedIn() {
      return false;
    }

    public String getEmail() {
      return null;
    }

    public boolean isAdmin() {
      return false;
    }

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
}
