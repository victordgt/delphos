package br.org.universa.web;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Future;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import br.org.universa.negocio.Usuario;
import br.org.universa.persistencia.UsuarioDAO;

import com.google.appengine.tools.development.ApiProxyLocal;
import com.google.appengine.tools.development.Clock;
import com.google.appengine.tools.development.LocalRpcService;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.ApiProxyException;
import com.google.apphosting.api.ApiProxy.Environment;
import com.google.apphosting.api.ApiProxy.LogRecord;



public class TestLogin extends TestCase{
	
	private WicketTester tester = new WicketTester(Login.class);
	
	@Before
	public void setUp() throws Exception{
	    super.setUp();
        ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
        //ApiProxyLocalImpl apiProxyLocalImpl = new ApiProxyLocalImpl(new File(".")){};
		//ApiProxy.setDelegate(apiProxyLocalImpl);

        
	/*	ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
		ApiProxyLocalFactory factory = new ApiProxyLocalFactory();
		LocalServerEnvironment localServerEnvironment = new LocalServerEnvironment() {
			@Override
			public void waitForServerToStart() throws InterruptedException {
				// TODO Auto-generated method stub
			}
			
			@Override
			public int getPort() {
				return 8080;
			}
			
			@Override
			public File getAppDir() {
				return new File(".");
			}
			
			@Override
			public String getAddress() {
				return "127.0.0.1";
			}
		};
        ApiProxy.setDelegate(factory.create(localServerEnvironment));*/
	}
	
	@Test
	public void testAutenticar() {	
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("usuario");
		usuario.setSenha("senha");
		new UsuarioDAO().salvaOuAltera(usuario);
		
		tester.startPage(Login.class);
		
		FormTester form = tester.newFormTester("loginForm");
		form.setValue("userId", "usuario");
		form.setValue("password", "senha");
		form.submit();
		
		tester.assertRenderedPage(Menu.class);
	}
	
	@Test
	public void testFalhaAutenticacao() {	
		tester.startPage(Login.class);
		
		FormTester form = tester.newFormTester("loginForm");
		form.setValue("userId", "");
		form.setValue("password", "");
		form.submit();
		
		tester.assertRenderedPage(Login.class);
	}	

}

class Teste implements ApiProxyLocal {

	@Override
	public void log(Environment arg0, LogRecord arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Future<byte[]> makeAsyncCall(Environment arg0, String arg1,
			String arg2, byte[] arg3, ApiConfig arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] makeSyncCall(Environment arg0, String arg1, String arg2,
			byte[] arg3) throws ApiProxyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clock getClock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalRpcService getService(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClock(Clock arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperties(Map<String, String> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}
