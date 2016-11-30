package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Disciplina;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Érico
 */
@Stateful
public class DisciplinaDAO<T> extends DAOGenerico<Disciplina> implements Serializable{
    public DisciplinaDAO(){
        super();
        super.setClassePersistente(Disciplina.class);
        super.setOrdem("nome");
    }
    
     public T getObjectById(String id) throws Exception{
        return (T) super.getEm().find(super.getClassePersistente(), id);
    }
    
    @Override
     public List<Disciplina> getListaObjetos() {
        String ejbql = "from " + super.getClassePersistente().getSimpleName() ;
        String where = "";
        super.setFiltro(protegerFiltro(super.getFiltro()));
        if (super.getFiltro().length() > 0) {
            if (super.getOrdem().equals("id")) {
                try {
                    Integer.parseInt(super.getFiltro());
                    where += " where " + super.getOrdem() + " = '" + super.getFiltro() + "' ";
                } catch (Exception e) {
                }
            } else {
                where += " where upper(" + super.getOrdem() + ") like '" + super.getFiltro().toUpperCase() + "%' ";
            }
        }
        ejbql += where;
        ejbql += " order by " + super.getOrdem();
        super.setTotalObjetos(super.getEm().createQuery("select nome from "
        + super.getClassePersistente().getSimpleName() + where
        + " order by " + super.getOrdem()).getResultList().size());
        return super.getEm().createQuery(ejbql).setFirstResult(super.getPosicaoAtual()).setMaxResults(super.getMaximoObjetos()).getResultList();
    }
}
