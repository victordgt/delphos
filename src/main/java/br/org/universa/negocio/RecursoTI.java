package br.org.universa.negocio;

import java.util.ArrayList;
import java.util.List;

public enum RecursoTI {
	
	SOFTWARE, HARDWARE;
	
	private static List<String> valores = new ArrayList<String>();
	static {
		for (RecursoTI recurso : values()) {
			valores.add(recurso.toString());
		}
		
	}
	
	public static List<String> valores() {
		return valores;
	}
}
