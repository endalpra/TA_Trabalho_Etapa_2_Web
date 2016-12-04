package br.edu.ifsul.controle;

import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

@Named(value = "controleDisciplina")
@SessionScoped
public class ControleDisciplina implements Serializable {
    @EJB
    private DisciplinaDAO<Disciplina> dao;
    private Disciplina objeto;
    private Boolean editando;
    
    public ControleDisciplina(){        
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/disciplina/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Disciplina();
    }
    
    public void alterar(Integer id){
      try {
            objeto = dao.getObjectById(id); 
            editando = true;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.geMensagemErro(e));            
        }                
    }
    
    public void excluir(Integer id){
       try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro a remover objeto: "+Util.geMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {            
            dao.persist(objeto);            
            Util.mensagemInformacao("Sucesso ao persistir objeto");  
            editando = false;        
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir: "+Util.geMensagemErro(e));            
        }                
    }
    
    public Disciplina getObjeto() {
        return objeto;
    }

    public void setObjeto(Disciplina objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public DisciplinaDAO<Disciplina> getDao() {
        return dao;
    }

    public void setDao(DisciplinaDAO<Disciplina> dao) {
        this.dao = dao;
    }
}
