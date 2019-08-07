package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bean.FuncionarioBean;
import bean.UsuarioBean;
import model.FuncionarioModel;
import model.UsuarioModel;

@SessionScoped
@ManagedBean(name = "autentication")
public class AutenticationMB {
	private FuncionarioBean funcionario;
	
	
	
	public AutenticationMB() {
		super();
		this.funcionario = new FuncionarioBean();
	}

	public void login() {
		if(funcionario==null)
			return;
		else {
			FuncionarioBean us = (new FuncionarioModel()).readBean(funcionario);
			if(us != null){
				funcionario = us;
				SystemMB.getSystem().redirect("/");
			}else {
				System.out.println("Usuario e/ou senha Incorretos!");
				(new MessagesMB()).warn("Usuario e/ou senha Incorretos!");
				//addMessage(, "Usuario e/ou senha Incorretos!");
			}
		}
	}

	public void logout() {
		SystemMB.getSystem().redirect("/p/autenticacao/login.xhtml");
	}

	public String testAutentication() {
		return "";
	}
	
	

	public FuncionarioBean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}

	public static AutenticationMB getAutentication() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec;
		HttpSession session;
		AutenticationMB au = null;
		try {
			if (fc != null) {
				ec = fc.getExternalContext();
				if (ec != null) {
					session = (HttpSession) ec.getSession(true);
					if (session != null) {
						au = ((AutenticationMB) session.getAttribute("autentication"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (au);
	}
}
