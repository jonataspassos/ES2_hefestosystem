package controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean(name = "system")
public class SystemMB {
	public String path() {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
	}

	public void redirect(String page) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SystemMB getSystem() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec;
		HttpSession session;
		SystemMB sys = null;
		try {
			if (fc != null) {
				ec = fc.getExternalContext();
				if (ec != null) {
					session = (HttpSession) ec.getSession(true);
					if (session != null) {
						sys = ((SystemMB) session.getAttribute("system"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (sys);
	}
}
