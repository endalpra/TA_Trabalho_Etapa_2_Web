/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Érico
 */
public class Util {
    
    public static void mensagemErro(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, facesMessage);
    }

    public static void mensagemInformacao(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, facesMessage);
    }

    public static String geMensagemErro(Exception e) {
        while (e.getCause() != null) {
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        return retorno;
    }
    
}
