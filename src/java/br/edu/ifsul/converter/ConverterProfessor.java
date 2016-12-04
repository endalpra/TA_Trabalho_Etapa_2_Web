package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Professor;
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
@FacesConverter(value="converterProfessor")
public class ConverterProfessor implements Serializable, Converter{

    @PersistenceContext(unitName = "TA_Trabalho_Etapa_2_WebPU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string==null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Professor.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o==null){
            return null;
        }
        Professor obj = (Professor) o;
        return obj.getId().toString();
    }

  }
