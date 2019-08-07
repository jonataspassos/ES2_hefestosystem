package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bean.AluguelBean;
import exemploTablePF.CarService;
import lookUp.AluguelLookUpList;
import model.AluguelModel;

@ManagedBean(name = "aluguel")
@ViewScoped
public class AluguelController implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<AluguelLookUpList> alugueis;
	
	private List<AluguelLookUpList> fil_alugueis;
	
	public List<AluguelLookUpList> getFil_alugueis() {
		return fil_alugueis;
	}

	private AluguelBean aluguel = new AluguelBean();
	
	@ManagedProperty("#{alguguelService}")
    private AluguelModel service;
	
	@PostConstruct
	public void init() {
		alugueis = service.list();
	}

	public List<AluguelLookUpList> getAlugueis() {
		return alugueis;
	}

	public void setService(AluguelModel service) {
		this.service = service;
	}

	public void setFil_alugueis(List<AluguelLookUpList> fil_alugueis) {
		this.fil_alugueis = fil_alugueis;
	}
	
	

}
