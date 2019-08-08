package controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import controller.MessagesMB;

import bean.MaquinaBean;
//import lookUp.UsuarioLookUpList;
import model.MaquinaModel;
import lookUp.MaquinaLookUp;

@ManagedBean(name="maquina")
@ViewScoped
@RequestScoped
public class MaquinaController {
	private MaquinaModel maqm;
	private MaquinaBean maquina;
	private ArrayList<MaquinaLookUp> maquinas; 
	
	@PostConstruct
	public void init() {
		maqm = new MaquinaModel();
		maquina = new MaquinaBean();
		maquinas = maqm.list();
	}

	public MaquinaModel getMaqm() {
		return maqm;
	}

	public void setMaqm(MaquinaModel maqm) {
		this.maqm = maqm;
	}

	public MaquinaBean getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaBean maquina) {
		this.maquina = maquina;
	}

	public ArrayList<MaquinaLookUp> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<MaquinaLookUp> maquinas) {
		this.maquinas = maquinas;
	}
	
	public void createMaquina() throws Exception {
		if(maqm.create(maquina)) {
			(new MessagesMB()).info("Máquina cadastrada com sucesso.");
			Thread.sleep(5000);
			SystemMB.getSystem().redirect("/p/maquina/listar.xhtml");
			return;
		}
		(new MessagesMB()).error("Error ao tentar cadastrar máquina.");
	}
}
