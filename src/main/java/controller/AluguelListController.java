package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lookUp.AluguelLookUpList;
import model.AluguelModel;

@ViewScoped
@ManagedBean(name = "aluguelListMB")
public class AluguelListController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AluguelLookUpList> alugueis;
	private List<AluguelLookUpList> filteredAlugueis;

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

	public void setAluguelService(AluguelModel aluguelService) {
		this.aluguelService = aluguelService;
	}

}
