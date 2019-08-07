package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bean.AluguelBean;
import lookUp.AluguelLookUpList;
import model.AluguelModel;

@ManagedBean(name = "aluguel")
@ViewScoped
public class AluguelController {
	private ArrayList<AluguelLookUpList> alugueis = new ArrayList<AluguelLookUpList>();
	private AluguelBean aluguel = new AluguelBean();

	public AluguelController() {
		super();
		// TODO Auto-generated constructor stub
		alugueis = (new AluguelModel()).list();
	}

	public ArrayList<AluguelLookUpList> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(ArrayList<AluguelLookUpList> alugueis) {
		this.alugueis = alugueis;
	}

	public AluguelBean getAluguel() {
		return aluguel;
	}

	public void setAluguel(AluguelBean aluguel) {
		this.aluguel = aluguel;
	}

}
