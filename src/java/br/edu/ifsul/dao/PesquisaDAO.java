package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pesquisa;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Érico
 */
@Stateful
public class PesquisaDAO<T> extends DAOGenerico<Pesquisa> implements Serializable{
    public PesquisaDAO(){
        super();
        super.setClassePersistente(Pesquisa.class);
    }
}
