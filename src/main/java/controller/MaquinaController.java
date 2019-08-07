package controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

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
}
