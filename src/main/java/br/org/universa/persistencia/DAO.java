package br.org.universa.persistencia;

import java.util.List;

import br.org.universa.negocio.Entidade;

public interface DAO<T extends Entidade> {

	public void exclui(T entidade);

	public T recupera(Long chave);

	public List<T> recuperaTodos();

	public void salvaOuAltera(T entidade);

	public T recuperaPorValorAtributo(Object valorAtributo);

}