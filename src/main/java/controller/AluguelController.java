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

import org.primefaces.PrimeFaces;

import bean.AluguelBean;
import lookUp.ClienteLookUpList;
import lookUp.EmpresaLookUpList;
import lookUp.MaquinaLookUp;
import model.AluguelModel;
import model.ClienteModel;
import model.EmpresaModel;
import model.MaquinaModel;
import resources.HUtil;

@ManagedBean(name = "aluguelMB")
@ViewScoped
public class AluguelController implements Serializable {

	private static final long serialVersionUID = -5413477193703904733L;

//------------------Select Beans------------------------------//
	private AluguelBean aluguelSel;

	private ClienteLookUpList clienteSel;
	private EmpresaLookUpList empresaSel;
	private MaquinaLookUp maquinaSel2;
	private List<MaquinaLookUp> maquinas;

//------------------Atributes--------------------------------//
	private int step = 0;
	private boolean empresa = false;
	private boolean consultFrom = true;
	private List<Date> period;
	private float[] descontos = { 1.0f, 1.0f, 1.0f, 1.0f };

//------------------Managed Propetys -------------------------//
	@ManagedProperty("#{aluguelModel}")
	private AluguelModel aluguelService;

	@ManagedProperty("#{clienteModel}")
	private ClienteModel clienteService;

	@ManagedProperty("#{empresaModel}")
	private EmpresaModel empresaService;

	@ManagedProperty("#{maquinaModel}")
	private MaquinaModel maquinaService;

	@PostConstruct
	public void init() {
		aluguelSel = new AluguelBean();
		clienteSel = new ClienteLookUpList();
		empresaSel = new EmpresaLookUpList();
		String param = getMaquina_id_param();
		if (param != null) {
			maquinaSel2 = maquinaService.read(param);
		} else {
			maquinaSel2 = new MaquinaLookUp();
		}
		maquinas = maquinaService.list(0);
		if (maquinas.size() == 0) {
			System.out.println("Não há máquinas para alugar.");
		}

		period = new ArrayList<Date>();
		period.add(new Date());
		period.add(period.get(0));

	}

	public void setAluguelService(AluguelModel aluguelService) {
		this.aluguelService = aluguelService;
	}

	public void setClienteService(ClienteModel clienteService) {
		this.clienteService = clienteService;
	}

	public void setEmpresaService(EmpresaModel empresaService) {
		this.empresaService = empresaService;
	}

	public void setMaquinaService(MaquinaModel maquinaService) {
		this.maquinaService = maquinaService;
	}

//-------------------- Beans Get and Set ------------------------//	

	public AluguelBean getAluguelSel() {
		return aluguelSel;
	}

	public void setAluguelSel(AluguelBean aluguelSel) {
		this.aluguelSel = aluguelSel;
	}

	public ClienteLookUpList getClienteSel() {
		return clienteSel;
	}

	public void setClienteSel(ClienteLookUpList clienteSel) {
		this.clienteSel = clienteSel;
	}

	public EmpresaLookUpList getEmpresaSel() {
		return empresaSel;
	}

	public void setEmpresaSel(EmpresaLookUpList empresaSel) {
		this.empresaSel = empresaSel;
	}

	public List<MaquinaLookUp> getMaquinas() {
		return maquinas;
	}

	public MaquinaLookUp getMaquinaSel2() {
		return maquinaSel2;
	}

	public void setMaquinaSel2(MaquinaLookUp maquinaSel2) {
		this.maquinaSel2 = maquinaSel2;
	}

//--------------------- Get And Set -----------------------------//
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

	public List<Date> getPeriod() {
		return period;
	}

	public void setPeriod(List<Date> period) {
		if (period.size() < 2)
			period.add(period.get(0));
		this.period = period;
	}

//------------------- Unreal Get and Set --------------//
	public String getDisabledAnterior() {
		if (0 != step)
			return "false";
		else
			return "true";
	}

	public String getValueProximo() {
		if (2 == step)
			return "Concluir";
		else
			return "Próximo";
	}

	public String showEmpresa() {
		if (empresa)
			return "inherit";
		else
			return "none";
	}

	public String showPlusCliente() {
		if (clienteSel.getNome() == null)
			return "inherit";
		else
			return "none";
	}

	public String showPlusEmpresa() {
		if (empresa && empresaSel.getRaz_social() == null)
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

	public String getMaquinaSel() {
		return getMaquinaSel2().toString();
	}

	public void setMaquinaSel(String maquinaSel) {
		String a[] = maquinaSel.split(" - ");
		for (int i = 0; i < getMaquinas().size(); i++) {
			if (a[0].equals("" + maquinas.get(i).getN_registro())) {
				maquinaSel2 = maquinas.get(i);
			}
		}

	}

	public List<String> getListHoras() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			list.add("" + i + "h");
		}
		return list;
	}

	public String getHoras() {
		System.out.println("buscando horas");
		String r = "" + (int) getAluguelSel().getTempo_hd() + "h";
		return r;
	}

	public void setHoras(String horas) {
		horas = horas.replaceAll("[^0-9]", "");
		System.out.println(horas);
		try {
			getAluguelSel().setTempo_hd(Integer.parseInt(horas));
		} catch (NumberFormatException e) {
			getAluguelSel().setTempo_hd(0);
		}

	}

	public int getNdias() {
		return (int) ((getPeriod().get(1).getTime() - getPeriod().get(0).getTime()) / 86400000 + 1);
	}

	public float getValor() {
		return getNdias() * getMaquinaSel2().getValor_diaria();
	}

	public String getDescNdias() {
		return String.format("%.2f %%", descontos[0] * 100);
	}

	public String getDescHdias() {
		return String.format("%.2f %%", descontos[1] * 100);
	}

	public String getDescFidel() {
		return String.format("%.2f %%", descontos[2] * 100);
	}

	public String getDescPers() {
		return String.format("%.2f %%", descontos[3] * 100);
	}

	public float valNdiasf() {
		return getValor() * descontos[0];
	}

	public float valHdiasf() {
		return valNdiasf() * descontos[1];
	}

	public float valFidelf() {
		return valHdiasf() * descontos[2];
	}

	public float valPersf() {
		return valFidelf() * descontos[3];
	}

	public String getValNdias() {
		return String.format("R$%.2f", valNdiasf());
	}

	public String getValHdias() {
		return String.format("R$%.2f", valHdiasf());
	}

	public String getValFidel() {
		return String.format("R$%.2f", valFidelf());
	}

	public String getValPers() {
		return String.format("R$%.2f", valPersf());
	}

	public String getDelNdias() {
		return String.format("R$%.2f", valNdiasf() - getValor());
	}

	public String getDelHdias() {
		return String.format("R$%.2f", valHdiasf() - valNdiasf());
	}

	public String getDelFidel() {
		return String.format("R$%.2f", valFidelf() - valHdiasf());
	}

	public String getDelPers() {
		return String.format("R$%.2f", valPersf() - valFidelf());
	}

	public void setTotal(String total) {
		getAluguelSel().setVal_contratado(HUtil.parseFloat(total));
	}

	public String getTotal() {
		return String.format("R$%.2f", getAluguelSel().getVal_contratado());
	}

//----------------- Methods ---------------------------//	
	public void updateDescontos() {
		descontos[3] = getAluguelSel().getVal_contratado() / valFidelf();
	}

	public void nextStep() {
		switch (step) {
		case 0:
			if (clienteSel.getNome() != null && (!empresa || empresaSel.getRaz_social() != null)) {
				//PrimeFaces.current().executeScript("$('.step0').css('display', 'none');");
				//PrimeFaces.current().executeScript("$('.step2').css('display', 'none');");
				this.step++;
				//PrimeFaces.current().executeScript("$('.step1').css('display', 'block');");
			} else {
				PrimeFaces.current().executeScript(
						"Swal.fire({ type: 'warning', title: 'Oopss...', text: 'Preencha todos os campos.'})");
			}
			break;
		case 1:
			if (maquinaSel2.getMarca() != null && getHoras() != null) {
				//PrimeFaces.current().executeScript("$('.step0').css('display', 'none');");
				//PrimeFaces.current().executeScript("$('.step1').css('display', 'none');");

				descontos[0] = HUtil.descNdias(getNdias());
				descontos[1] = HUtil.descHorasDias(getAluguelSel().getTempo_hd());
				descontos[2] = HUtil
						.descVip(empresa ? getEmpresaSel().getN_alugueis() : getClienteSel().getN_alugueis());
				setTotal("" + getValor() * descontos[0] * descontos[1] * descontos[2]);

				this.step++;
				//PrimeFaces.current().executeScript("$('.step2').css('display', 'block');");
			} else {
				PrimeFaces.current().executeScript(
						"Swal.fire({ type: 'warning', title: 'Oopss...', text: 'Preencha todos os campos.'})");
			}
			break;
		case 2:
			try {
				save();
			} catch (Exception e) {
				PrimeFaces.current().executeScript(
						"Swal.fire({ type: 'error', title: 'Oopss...', text: 'Impossível realizar aluguel.'})");
			}
			break;
		}
	}

	public void backStep() {
		if (this.step > 0) {
			switch (step) {
			case 1:
				//PrimeFaces.current().executeScript("$('.step1').css('display', 'none');");
				//PrimeFaces.current().executeScript("$('.step2').css('display', 'none');");
				break;
			case 2:
				//PrimeFaces.current().executeScript("$('.step0').css('display', 'none');");
				//PrimeFaces.current().executeScript("$('.step2').css('display', 'none');");
				break;
			}
			this.step--;
			//PrimeFaces.current().executeScript("$('.step" + this.step + "').css('display', 'block');");
		}

	}

	public void save() throws Exception {
		getAluguelSel().setN_funcionario_fk(AutenticationMB.getFuncionarioDaSessao().getN_funcionario());
		aluguelSel.setN_cliente_fk(getClienteSel().getN_cliente());
		aluguelSel.setN_maquina_fk(getMaquinaSel2().getN_maquina());
		aluguelSel.setData_ini(getPeriod().get(0));
		aluguelSel.setData_final(period.get(1));

		int id = aluguelService.create(aluguelSel);

		if (empresa) {
			aluguelSel.setN_aluguel(id);
			aluguelSel.setN_empresa_fk(getEmpresaSel().getN_empresa());
			aluguelService.setEmpresa(aluguelSel);
		}

		System.out.println("Máquina Alugada");
		PrimeFaces.current().executeScript(
				"Swal.fire({ type: 'success', title: 'Tudo certo...', text: 'Máquina Alugada.', timer: 3000,"
						+ "showConfirmButton: false})");

		Thread.sleep(3000);
		SystemMB.getSystem().redirect("/p/aluguel/listar.xhtml");
	}

	public String getForms(int i) {
		if (i == step)
			return "inherit";
		else
			return "none";
	}

	public void searchCliente() {
		String cpf = this.getClienteSel().getCpf();
		this.clienteSel = clienteService.readLookUp(cpf);
		if (this.clienteSel == null) {
			clienteSel = new ClienteLookUpList();
			this.clienteSel.setCpf(cpf);
			System.out.println("Esse Cliente não Existe!");
			PrimeFaces.current().executeScript(
					"Swal.fire({ type: 'warning', title: 'Oopss...', text: 'Esse Cliente não Existe!'})");
		}
	}

	public void searchEmpresa() {
		String cnpj = this.getEmpresaSel().getCnpj();
		this.empresaSel = empresaService.read(this.empresaSel.getCnpj());
		if (this.empresaSel == null) {
			empresaSel = new EmpresaLookUpList();
			this.empresaSel.setCnpj(cnpj);
		}
	}

}
