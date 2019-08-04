package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean(name="autentication")
public class AutenticationMB {
	public void login() {
		System.out.println("Ta entrando login");
		SystemMB.getSystem().redirect("/");
	}
	public void logout() {
		System.out.println("Ta entrando logout");
		SystemMB.getSystem().redirect("/p/login.xhtml");
	}
	public String testAutentication() {
		return "";}
	
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
