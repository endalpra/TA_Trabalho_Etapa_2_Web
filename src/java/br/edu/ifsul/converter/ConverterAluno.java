package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Érico
 */
@FacesConverter(value="converterAluno")
public class ConverterAluno implements Serializable, Converter{

    @PersistenceContext(unitName = "TA_Trabalho_Etapa_2_WebPU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string==null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Aluno.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o==null){
            return null;
        }
        Aluno obj = (Aluno) o;
        return obj.getId().toString();
    }

  }
