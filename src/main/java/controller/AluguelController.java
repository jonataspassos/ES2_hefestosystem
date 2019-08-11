package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bean.AluguelBean;
import lookUp.AluguelLookUpList;
import lookUp.ClienteLookUpList;
import lookUp.EmpresaLookUpList;
import lookUp.MaquinaLookUp;
import model.AluguelModel;
import model.ClienteModel;
import model.EmpresaModel;
import model.MaquinaModel;

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
	private MaquinaLookUp maquinaSel;
	private List<MaquinaLookUp> maquinas;
	
	private int step = 0;
	private boolean empresa = false;
	private boolean consultFrom = true;
	private List<Date>period;

	@ManagedProperty("#{aluguelModel}")
	private AluguelModel aluguelService;
	
	@ManagedProperty("#{clienteModel}")
	private ClienteModel clienteService;
	
	@ManagedProperty("#{empresaModel}")
	private EmpresaModel empresaService;
	
	@ManagedProperty("#{maquinaModel}")
	private MaquinaModel maquinaService;
	
	@ManagedProperty("#{message}")
	private MessagesMB messageService;

	@PostConstruct
	public void init() {
		
	}
	
	public void nextStep() {
		if(clienteSel.getNome()!=null) {
		if(this.step<2)
			this.step++;
		}else {
			
		}
		
	}
	
	public void backStep() {
		if(this.step>0)
			this.step--;
		
	}
	
	
	
	public void save() {
		getAluguelSel().setN_funcionario_fk(AutenticationMB.getFuncionarioDaSessao().getN_funcionario());
		getAluguelSel().setN_cliente_fk(getClienteSel().getN_cliente());
		getAluguelSel().setN_maquina_fk(getMaquinaSel().getN_maquina());
		if(empresa) {
			getAluguelSel().setN_empresa_fk(getEmpresaSel().getN_empresa());
		}
	}
	
	public void cancel() {
		
	}

	public String getForms(int i) {
		if(i==step)
			return "inherit";
		else
			return "none";
	}
	
	public void searchCliente() {
		String cpf = this.getClienteSel().getCpf();
		this.clienteSel = clienteService.readLookUp(this.clienteSel.getCpf());
		if(this.clienteSel != null)
			this.getAluguelSel().setN_cliente_fk(this.clienteSel.getN_cliente());
		else {
			this.getClienteSel().setCpf(cpf);
			System.out.println("Esse Cliente n�o Existe!");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Esse Cliente n�o Existe!"));
			
		}
	}

	public String showEmpresa() {
		if(empresa)
			return "inherit";
		else
			return "none";
	}
	
	public void searchEmpresa() {
		String cnpj = this.getEmpresaSel().getCnpj();
		this.empresaSel = empresaService.read(this.empresaSel.getCnpj());
		if(this.empresaSel != null)
			this.getAluguelSel().setN_empresa_fk(this.empresaSel.getN_empresa());
		else {
			this.getEmpresaSel().setCnpj(cnpj);
		}
	}
	
	public String showPlusCliente() {
		if(clienteSel.getNome()==null)
			return "inherit";
		else
			return "none";
	}
	
	public String showPlusEmpresa() {
		if(empresaSel.getRaz_social()==null)
			return "inherit";
		else
			return "none";
	}
	
	public String getMaquina_id_param() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String maquina_id = paramMap.get("maquina_id");

		return maquina_id;
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

	public MaquinaLookUp getMaquinaSel() {
		if(maquinaSel == null) {
			maquinaSel = new MaquinaLookUp();
			if(consultFrom) {
				String i = getMaquina_id_param();
				if(i != null) {
					maquinaSel.setN_maquina(Integer.parseInt(i));
				}else {
					consultFrom = false;
				}
			}
		}
		return maquinaSel;
	}

	public void setMaquinaSel(MaquinaLookUp maquinaSel) {
		this.maquinaSel = maquinaSel;
	}
	public void setClienteService(ClienteModel clienteService) {
		this.clienteService = clienteService;
	}	

	public void setEmpresaService(EmpresaModel empresaService) {
		this.empresaService = empresaService;
	}

	public void setMessageService(MessagesMB messageService) {
		this.messageService = messageService;
	}

	public MessagesMB getMessageService() {
		return messageService;
	}

	public void setMaquinaService(MaquinaModel maquinaService) {
		this.maquinaService = maquinaService;
	}

	public List<Date> getPeriod() {
		if(period == null) {
			period = new ArrayList<Date>();
		}
		if(period.size()>1) {
			System.out.println(period.get(0));
			System.out.println(period.get(1));
		}
		return period;
	}

	public void setPeriod(List<Date> period) {
		this.period = period;
	}

	public List<MaquinaLookUp> getMaquinas() {
		if(maquinas == null) {
			maquinas = maquinaService.list();
			System.out.println(maquinas);
		}
		return maquinas;
	}
	
	
	
}

