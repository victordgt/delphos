package br.org.universa.web;

import org.apache.wicket.model.LoadableDetachableModel;
import br.org.universa.negocio.CadastroBase;
import br.org.universa.persistencia.CadastroBaseDAO;

public class DetachableCadastroModel extends LoadableDetachableModel<CadastroBase> {

	private static final long serialVersionUID = 1L;
	private CadastroBaseDAO dao = new CadastroBaseDAO();
    private final long id;

    public DetachableCadastroModel(CadastroBase cadastro) {
	        this(cadastro.getId());
	}

    public DetachableCadastroModel(long id) {
        if (id == 0)
        {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        else if (obj == null)
        {
            return false;
        }
        else if (obj instanceof DetachableCadastroModel)
        {
            DetachableCadastroModel other = (DetachableCadastroModel)obj;
            return other.id == id;
        }
        return false;
    }

    @Override
    protected CadastroBase load() {
        // loads contact from the database
        return dao.recupera(id);
    }	
}
