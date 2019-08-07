
package bean_exemplo_depois_apagar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="jorge")
@ViewScoped
public class Qualquercoisa {
	private String coisa;
	
	private int step = 0;

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getCoisa() {
		return coisa;
	}

	public void setCoisa(String coisa) {
		this.coisa = coisa;
	}
	
	public void print() {
		System.out.println("Este é o parametro"+ coisa);
		addMessage("Este é o parametro"+ coisa);
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
