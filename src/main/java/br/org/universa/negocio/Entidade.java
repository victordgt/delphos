package br.org.universa.negocio;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.appengine.api.datastore.Key;


@MappedSuperclass
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;

	public void setId(Key id) {
		this.id = id;
	}

	public Key getId() {
		return id;
	}
	

}
