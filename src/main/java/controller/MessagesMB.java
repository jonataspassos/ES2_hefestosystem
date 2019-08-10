package controller;

import javax.faces.context.FacesContext;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "message")
@ApplicationScoped
public class MessagesMB{

	public void info(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Tudo certo!", message));
	}

	public void warn(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", message));
	}

	public void error(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", message));
	}

	public void fatal(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro!", message));
	}
}