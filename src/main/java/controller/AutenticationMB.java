package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bean.FuncionarioBean;
import model.FuncionarioModel;

@SessionScoped
@ManagedBean(name = "autentication")
public class AutenticationMB {
	private FuncionarioBean funcionario;
	private String senha1;
	private String senha2;

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
				if(funcionario.getStatus().equals("new"))
					SystemMB.getSystem().redirect("/p/autenticacao/primeiro_acesso.xhtml");
				else
					SystemMB.getSystem().redirect("/");
			}else {
				//System.out.println("Usuario e/ou senha Incorretos!");
				(new MessagesMB()).warn("Usuario e/ou senha Incorretos!");
				//addMessage(, "Usuario e/ou senha Incorretos!");
			}
		}
	}

	public void redefinirSenha() {
		FuncionarioModel funm = new FuncionarioModel();
		funm.novaSenha(funcionario, senha1, senha2);
		SystemMB.getSystem().redirect("/");
	}
	
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		
		try {
			if (session != null) {
				session.removeAttribute("autentication");
				session.invalidate();
			}
			SystemMB.getSystem().redirect("/p/autenticacao/login.xhtml");

			this.finalize();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	public boolean logado() {
		return this.funcionario.getStatus() !=null;
	}
	
	public String testAutentication() {
		System.out.println(this);
		System.out.println(SystemMB.getSystem());
		if(!logado()) {
			SystemMB.getSystem().redirect("/p/autenticacao/login.xhtml");
		}
		return "Logado";
	}
	
	

	public FuncionarioBean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}
	
	public String getSenha1() {
		return senha1;
	}

	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
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
	
	public static FuncionarioBean getFuncionarioDaSessao() {
		return getAutentication().getFuncionario();
	}
}
