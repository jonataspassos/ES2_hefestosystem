package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lookUp.FuncionarioLookUp;
import model.FuncionarioModel;

@ManagedBean(name="funcionarioMB")
@ViewScoped
public class FuncionarioController {
	private List<FuncionarioLookUp>funcionarios;
	private List<FuncionarioLookUp>filteredFuncionarios;
	private FuncionarioLookUp selectedFuncionario;
	
	@ManagedProperty("#{funcionarioModel}")
	private FuncionarioModel funcionarioService;
	
	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.list();
	}

	public List<FuncionarioLookUp> getFilteredFuncionarios() {
		return filteredFuncionarios;
	}

	public void setFilteredFuncionarios(List<FuncionarioLookUp> filteredFuncionarios) {
		this.filteredFuncionarios = filteredFuncionarios;
	}

	public FuncionarioLookUp getSelectedFuncionario() {
		return selectedFuncionario;
	}

	public void setSelectedFuncionario(FuncionarioLookUp selectedFuncionario) {
		this.selectedFuncionario = selectedFuncionario;
	}

	public List<FuncionarioLookUp> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarioService(FuncionarioModel funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	
}
