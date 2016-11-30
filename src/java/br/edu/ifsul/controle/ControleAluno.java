package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.PesquisaDAO;
import br.edu.ifsul.modelo.Aluno;
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
@Named(value = "controleAluno")
@SessionScoped
public class ControleAluno implements Serializable{
   
    @EJB
    private AlunoDAO<Aluno> dao;
    private Aluno objeto;
    private Boolean editando;
    @EJB
    private PesquisaDAO<Pesquisa> daoPesquisa;
    @EJB
    private DisciplinaDAO<Disciplina> daoDisciplina;
    private Disciplina disciplina;
    private Boolean editandoDisciplina;

    public ControleAluno() {
        editando = false;
        editandoDisciplina = false;
    }

    public String listar() {
        editando = false;
        editandoDisciplina = false;
        return "/privado/aluno/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Aluno();
        editando = true;
        editandoDisciplina = false;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoDisciplina = false;
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

    public void novaDisciplina() {
        editandoDisciplina = true;
    }

    public void salvarDisciplina() {
        if (!objeto.getDisciplinas().contains(disciplina)) {
            objeto.getDisciplinas().add(disciplina);
            editandoDisciplina = false;
            Util.mensagemInformacao("Disciplina adicionada com sucesso!");
        } else {
            Util.mensagemErro("Aluno já possui esta disciplina!");
        }
    }

    public void removerDisciplina(Disciplina obj){
        objeto.getDisciplinas().remove(obj);
        Util.mensagemInformacao("Disciplina removida com sucesso!");
    }

    public AlunoDAO<Aluno> getDao() {
        return dao;
    }

    public void setDao(AlunoDAO<Aluno> dao) {
        this.dao = dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Boolean getEditandoDisciplina() {
        return editandoDisciplina;
    }

    public void setEditandoDisciplina(Boolean editandoDisciplina) {
        this.editandoDisciplina = editandoDisciplina;
    }

    public PesquisaDAO<Pesquisa> getDaoPesquisa() {
        return daoPesquisa;
    }

    public void setDaoPesquisa(PesquisaDAO<Pesquisa> daoPesquisa) {
        this.daoPesquisa = daoPesquisa;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }
    
    
}
