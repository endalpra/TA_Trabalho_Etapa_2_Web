package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Érico
 */
@Stateful
public class AlunoDAO<T> extends DAOGenerico<Aluno> implements Serializable{
    
    public AlunoDAO(){
        super();
        super.setClassePersistente(Aluno.class);
    }
    
    @Override
    public Aluno getObjectById(Integer id) throws Exception {
        Aluno obj
                = (Aluno) super.getEm().find(super.getClassePersistente(), id);
        obj.getDisciplinas().size();
        return obj;
    }
    
   public Aluno localizaPorNomeAluno(String aluno){
       Query query = super.getEm().createQuery("from Aluno where upper(nome) = :aluno");
       query.setParameter("aluno",aluno.toUpperCase());
       Aluno obj = (Aluno) query.getSingleResult();
       return obj;
   }
   
   public T getObjectById(String id) throws Exception{
        return (T) super.getEm().find(super.getClassePersistente(), id);
    }
    
    @Override
     public List<Aluno> getListaObjetos() {
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
        super.setTotalObjetos(super.getEm().createQuery("select id from "
        + super.getClassePersistente().getSimpleName() + where
        + " order by " + super.getOrdem()).getResultList().size());
        return super.getEm().createQuery(ejbql).setFirstResult(super.getPosicaoAtual()).setMaxResults(super.getMaximoObjetos()).getResultList();
    }
    
}
