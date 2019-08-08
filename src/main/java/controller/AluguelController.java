package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lookUp.AluguelLookUpList;
import model.AluguelModel;

@ManagedBean(name = "aluguelMB")
@ViewScoped
public class AluguelController implements Serializable {
	private List<AluguelLookUpList> alugueis;
	private List<AluguelLookUpList> filteredAlugueis;
	private AluguelLookUpList selectedAluguel;

	@ManagedProperty("#{aluguelModel}")
	private AluguelModel aluguelService;

	@PostConstruct
	public void init() {
		alugueis = aluguelService.list();
	}

	public List<AluguelLookUpList> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<AluguelLookUpList> alugueis) {
		this.alugueis = alugueis;
	}

	public List<AluguelLookUpList> getFilteredAlugueis() {
		return filteredAlugueis;
	}

	public void setFilteredAlugueis(List<AluguelLookUpList> filteredAlugueis) {
		this.filteredAlugueis = filteredAlugueis;
	}

	public AluguelLookUpList getSelectedAluguel() {
		return selectedAluguel;
	}

	public void setSelectedAluguel(AluguelLookUpList selectedAluguel) {
		this.selectedAluguel = selectedAluguel;
	}

	public AluguelModel getAluguelService() {
		return aluguelService;
	}

	public void setAluguelService(AluguelModel aluguelService) {
		this.aluguelService = aluguelService;
	}

	
}
