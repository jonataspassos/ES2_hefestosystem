package controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

@ManagedBean (name="message")
public class MessagesMB {
     
    public void info(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }
     
    public void warn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", message));
    }
     
    public void error(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", message));
    }
     
    public void fatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro!", message));
    }
    
    public static MessagesMB getMessage() {
    	FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec;
		HttpSession session;
		MessagesMB msg = null;
		try {
			if (fc != null) {
				ec = fc.getExternalContext();
				if (ec != null) {
					session = (HttpSession) ec.getSession(true);
					if (session != null) {
						msg = ((MessagesMB) session.getAttribute("message"));
						if(msg == null) {
							msg = new MessagesMB();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new MessagesMB();
		}
		return (msg);
    }
}