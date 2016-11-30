package br.edu.ifsul.converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Érico
 */
@FacesConverter(value = "converterCalendar")
public class ConverterCalendar implements Serializable, Converter{

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string==null){
            return null;
        }
        Calendar obj = Calendar.getInstance();
        try{
            obj.setTime(sdf.parse(string));
            return obj;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Calendar obj = (Calendar) o;
        return sdf.format(obj.getTime());
    }
    
}

