package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lookUp.EmpresaLookUpList;
import model.EmpresaModel;

@ManagedBean(name = "empresaMB")
@ViewScoped
public class EmpresaController implements Serializable {
	private List<EmpresaLookUpList> empresas;
	private List<EmpresaLookUpList> filteredEmpresas;
	private EmpresaLookUpList selectedEmpresa;

	@ManagedProperty("#{empresaModel}")
	private EmpresaModel empresaService;

	@PostConstruct
	public void init() {
		empresas = empresaService.list();
	}

	public List<EmpresaLookUpList> getFilteredEmpresas() {
		return filteredEmpresas;
	}

	public void setFilteredEmpresas(List<EmpresaLookUpList> filteredEmpresas) {
		this.filteredEmpresas = filteredEmpresas;
	}

	public EmpresaLookUpList getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public void setSelectedEmpresa(EmpresaLookUpList selectedEmpresa) {
		this.selectedEmpresa = selectedEmpresa;
	}

	public List<EmpresaLookUpList> getEmpresas() {
		return empresas;
	}

	public void setEmpresaService(EmpresaModel empresaService) {
		this.empresaService = empresaService;
	}

}
