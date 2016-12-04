package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.PesquisaDAO;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Pesquisa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Érico
 */
@Named(value = "controleProfessor")
@SessionScoped
public class ControleProfessor implements Serializable{
   
    @EJB
    private ProfessorDAO<Professor> dao;
    private Professor objeto;
    private Boolean editando;
    @EJB
    private PesquisaDAO<Pesquisa> daoPesquisa;
  

    public ControleProfessor() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/professor/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Professor();
        editando = true;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.geMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.geMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.geMensagemErro(e));
        }
    }

    public ProfessorDAO<Professor> getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO<Professor> dao) {
        this.dao = dao;
    }

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public PesquisaDAO<Pesquisa> getDaoPesquisa() {
        return daoPesquisa;
    }

    public void setDaoPesquisa(PesquisaDAO<Pesquisa> daoPesquisa) {
        this.daoPesquisa = daoPesquisa;
    }

    
    
}
