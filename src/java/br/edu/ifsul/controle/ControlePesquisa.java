package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.PesquisaDAO;
import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Pesquisa;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Érico
 */
@Named(value = "controlePesquisa")
@SessionScoped
public class ControlePesquisa implements Serializable {

    @EJB
    private PesquisaDAO<Pesquisa> dao;    
    private Pesquisa objeto;
    private Boolean editando;    
    @EJB
    private AlunoDAO<Aluno> daoAluno;    
    private Aluno aluno;
    private Boolean editandoAluno;
    @EJB
    private ProfessorDAO<Professor> daoProfessor;    
    private Professor professor;
    private Boolean editandoProfessor;

    public ControlePesquisa() {
        editando = false;
        editandoAluno = false;
        editandoProfessor = false;
    }

    public String listar() {
        editando = false;
        editandoAluno = false;
        editandoProfessor = false;
        return "/privado/pesquisa/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Pesquisa();
        editando = true;
        editandoAluno = false;
        editandoProfessor = false;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoAluno = false;
            editandoProfessor = false;
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

    public void novoAluno() {
        editandoAluno = true;
    }

    public void novoProfessor() {
        editandoProfessor = true;
    }

    public void salvarAluno() {
        if (!objeto.getAlunos().contains(aluno)) {
            objeto.getAlunos().add(aluno);
            editandoAluno = false;
            Util.mensagemInformacao("Aluno adicionado com sucesso!");
        } else {
            Util.mensagemErro("Pesquisa já possui este aluno!");
        }
    }

    public void salvarProfessor() {
        if (!objeto.getProfessores().contains(professor)) {
            objeto.getProfessores().add(professor);
            editandoProfessor = false;
            Util.mensagemInformacao("Professor adicionado com sucesso!");
        } else {
            Util.mensagemErro("Pesquisa já possui este professor!");
        }
    }

    public void removerAluno(Aluno obj) {
        objeto.getAlunos().remove(obj);
        Util.mensagemInformacao("Aluno removido com sucesso!");
    }

    public void removerProfessor(Professor obj) {
        objeto.getProfessores().remove(obj);
        Util.mensagemInformacao("Professor removido com sucesso!");
    }

    public PesquisaDAO<Pesquisa> getDao() {
        return dao;
    }

    public void setDao(PesquisaDAO<Pesquisa> dao) {
        this.dao = dao;
    }

    public Pesquisa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pesquisa objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Boolean getEditandoAluno() {
        return editandoAluno;
    }

    public void setEditandoAluno(Boolean editandoAluno) {
        this.editandoAluno = editandoAluno;
    }

    public ProfessorDAO<Professor> getDaoProfessor() {
        return daoProfessor;
    }

    public void setDaoProfessor(ProfessorDAO<Professor> daoProfessor) {
        this.daoProfessor = daoProfessor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Boolean getEditandoProfessor() {
        return editandoProfessor;
    }

    public void setEditandoProfessor(Boolean editandoProfessor) {
        this.editandoProfessor = editandoProfessor;
    }

}
