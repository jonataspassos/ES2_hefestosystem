package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bean.AluguelBean;
import bean.ClienteBean;
import bean.EmpresaBean;
import bean.MaquinaBean;
import lookUp.AluguelLookUpList;
import lookUp.ClienteLookUpList;
import lookUp.EmpresaLookUpList;
import model.AluguelModel;
import model.ClienteModel;
import model.EmpresaModel;

@ManagedBean(name = "aluguelMB")
@ViewScoped
public class AluguelController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5413477193703904733L;
	
	private List<AluguelLookUpList> alugueis;
	private List<AluguelLookUpList> filteredAlugueis;
	
	private AluguelBean aluguelSel;
	
	private ClienteLookUpList clienteSel;
	private EmpresaLookUpList empresaSel;
	private MaquinaBean maquinaSel;
	
	
	private int step = 0;
	private boolean empresa = false;

	@ManagedProperty("#{aluguelModel}")
	private AluguelModel aluguelService;
	
	@ManagedProperty("#{clienteModel}")
	private ClienteModel clienteService;
	
	@ManagedProperty("#{empresaModel}")
	private EmpresaModel empresaService;

	@PostConstruct
	public void init() {
		
	}

	public List<AluguelLookUpList> getAlugueis() {
		if(alugueis == null)
			alugueis = aluguelService.list();
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

	public AluguelModel getAluguelService() {
		return aluguelService;
	}

	public void setAluguelService(AluguelModel aluguelService) {
		this.aluguelService = aluguelService;
	}
	
	public AluguelBean getAluguelSel() {
		if(aluguelSel == null)
			aluguelSel = new AluguelBean();
		return aluguelSel;
	}

	public void setAluguelSel(AluguelBean aluguelSel) {
		this.aluguelSel = aluguelSel;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	public void nextStep() {
		if(this.step<2)
			this.step++;
		
	}
	
	public void backStep() {
		if(this.step>0)
			this.step--;
		
	}
	
	public void cancel() {
		
	}

	public String getForms(int i) {
		if(i==step)
			return "inherit";
		else
			return "none";
	}

	public boolean isEmpresa() {
		return empresa;
	}

	public void setEmpresa(boolean empresa) {
		this.empresa = empresa;
	}

	public ClienteLookUpList getClienteSel() {
		if(clienteSel == null)
			clienteSel = new ClienteLookUpList();
		return clienteSel;
	}

	public void setClienteSel(ClienteLookUpList clienteSel) {
		this.clienteSel = clienteSel;
	}

	public EmpresaLookUpList getEmpresaSel() {
		if(empresaSel == null)
			empresaSel = new EmpresaLookUpList();
		return empresaSel;
	}

	public void setEmpresaSel(EmpresaLookUpList empresaSel) {
		this.empresaSel = empresaSel;
	}

	public MaquinaBean getMaquinaSel() {
		if(maquinaSel == null)
			maquinaSel = new MaquinaBean();
		return maquinaSel;
	}

	public void setMaquinaSel(MaquinaBean maquinaSel) {
		this.maquinaSel = maquinaSel;
	}
	public void setClienteService(ClienteModel clienteService) {
		this.clienteService = clienteService;
	}
	
	public void searchCliente() {
		this.clienteSel = clienteService.read(this.clienteSel.getCpf());
		if(this.clienteSel != null)
			this.getAluguelSel().setN_cliente_fk(this.clienteSel.getN_cliente());
		else {
			
		}
	}

	public String showEmpresa() {
		if(empresa)
			return "inherit";
		else
			return "none";
	}

	public void setEmpresaService(EmpresaModel empresaService) {
		this.empresaService = empresaService;
	}
	
	public void searchEmpresa() {
		this.empresaSel = empresaService.read(this.empresaSel.getCnpj());
		if(this.empresaSel != null)
			this.getAluguelSel().setN_empresa_fk(this.empresaSel.getN_empresa());
		else {
			
		}
	}
	
}
