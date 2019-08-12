package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import bean.AluguelBean;
import bean.FuncionarioBean;
import lookUp.ClienteLookUpList;
import lookUp.EmpresaLookUpList;
import lookUp.FuncionarioLookUp;
import lookUp.MaquinaLookUp;
import model.AluguelModel;
import model.ClienteModel;
import model.EmpresaModel;
import model.FuncionarioModel;
import model.MaquinaModel;
import resources.HUtil;

@ViewScoped
@ManagedBean(name = "aluguelConsultMB")
public class AluguelConsultController implements Serializable {

	// ------------------Select Beans------------------------------//
	private AluguelBean aluguelSel;

	private ClienteLookUpList clienteSel;
	private EmpresaLookUpList empresaSel;
	private MaquinaLookUp maquinaSel;
	private FuncionarioLookUp funcionarioSel;

	// ----------------------Atributes-----------------------------//
	private float[] descontos = { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f };
	// atraso, abuso, n_dias, horas, personalizado
	private Date retorno;
	private int step = 0;
	private float totalTemp;
	private float dinheiro = 0;

	// ------------------Managed Propetys -------------------------//
	@ManagedProperty("#{aluguelModel}")
	private AluguelModel aluguelService;

	@ManagedProperty("#{clienteModel}")
	private ClienteModel clienteService;

	@ManagedProperty("#{empresaModel}")
	private EmpresaModel empresaService;

	@ManagedProperty("#{maquinaModel}")
	private MaquinaModel maquinaService;
	
	@ManagedProperty("#{funcionarioModel}")
	private FuncionarioModel funcionarioService;

	@ManagedProperty("#{message}")
	private MessagesMB messageService;

	@PostConstruct
	public void init() {
		String param = getAluguel_id_param();
		if (param != null) {
			aluguelSel = aluguelService.readBean(Integer.parseInt(param));
			if (aluguelSel != null) {
				clienteSel = clienteService.readLookUp(aluguelSel.getN_cliente_fk());
				maquinaSel = maquinaService.read("" + aluguelSel.getN_maquina_fk());
				funcionarioSel = funcionarioService.read(aluguelSel.getN_funcionario_fk());
				if (aluguelSel.getN_empresa_fk() != -1) {
					empresaSel = empresaService.read(aluguelSel.getN_empresa_fk());
				}
				return;
			}
		} // else
		SystemMB.getSystem().redirect("/p/aluguel/listar.xhtml");

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

	public void setFuncionarioService(FuncionarioModel funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public void setMessageService(MessagesMB messageService) {
		this.messageService = messageService;
	}
	// -----------------------Beans get Sets ----------------------//

	public AluguelBean getAluguelSel() {
		return aluguelSel;
	}

	public ClienteLookUpList getClienteSel() {
		return clienteSel;
	}

	public EmpresaLookUpList getEmpresaSel() {
		return empresaSel;
	}

	public MaquinaLookUp getMaquinaSel() {
		return maquinaSel;
	}

	public FuncionarioLookUp getFuncionarioSel() {
		return funcionarioSel;
	}

	// --------------------------Get Set ---------------------------//
	public Date getRetorno() {
		if (retorno == null)
			retorno = new Date();
		return retorno;
	}

	public void setRetorno(Date retorno) {
		this.retorno = retorno;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getStep() {
		return step;
	}

	// ----------------------Unreall Get Set -----------------------//
	public String getDisabledAnterior() {
		if (0 != step)
			return "false";
		else
			return "true";
	}

	public String getValueProximo() {
		if (1 == step)
			return "Concluir";
		else
			return "Próximo";
	}

	public String getMotivo() {
		return "não sei";
	}

	public int getNdias() {
		return (int) ((getRetorno().getTime() - aluguelSel.getData_ini().getTime()) / 86400000 + 1);
	}

	public int getNdiasPrev() {
		return (int) ((aluguelSel.getData_final().getTime() - aluguelSel.getData_ini().getTime()) / 86400000 + 1);
	}

	public float getValor() {
		return getNdiasPrev() * maquinaSel.getValor_diaria();
	}

	public String getDescAbDias() {
		return String.format("%.2f %%", descontos[0] * 100);
	}

	public String getDescAbHoras() {
		return String.format("%.2f %%", descontos[1] * 100);
	}

	public String getDescNdias() {
		return String.format("%.2f %%", descontos[2] * 100);
	}

	public String getDescHdias() {
		return String.format("%.2f %%", descontos[3] * 100);
	}

	public String getDescPers() {
		return String.format("%.2f %%", descontos[4] * 100);
	}

	public float valAbDiasf() {
		return getValor() * descontos[0];
	}

	public float valAbHorasf() {
		return valAbDiasf() * descontos[1];
	}

	public float valNdiasf() {
		return valAbHorasf() * descontos[2];
	}

	public float valHdiasf() {
		return valNdiasf() * descontos[3];
	}

	public float valPersf() {
		return valHdiasf() * descontos[4];
	}

	public String getValAbDias() {
		return String.format("R$%.2f", valAbDiasf());
	}

	public String getValAbHoras() {
		return String.format("R$%.2f", valAbHorasf());
	}

	public String getValNdias() {
		return String.format("R$%.2f", valNdiasf());
	}

	public String getValHdias() {
		return String.format("R$%.2f", valHdiasf());
	}

	public String getValPers() {
		return String.format("R$%.2f", valPersf());
	}

	public String getDelAbDias() {
		return String.format("R$%.2f", valAbDiasf() - getValor());
	}

	public String getDelAbHoras() {
		return String.format("R$%.2f", valAbHorasf() - valAbDiasf());
	}

	public String getDelNdias() {
		return String.format("R$%.2f", valNdiasf() - valAbHorasf());
	}

	public String getDelHdias() {
		return String.format("R$%.2f", valHdiasf() - valNdiasf());
	}

	public String getDelPers() {
		return String.format("R$%.2f", valPersf() - valHdiasf());
	}

	public void setTotal(String total) {
		float convert;
		try {
			convert = Float.parseFloat(total);
		} catch (NumberFormatException e) {
			total = total.replaceAll("[^0-9,]", "");
			total = total.replaceAll(",", ".");
			convert = Float.parseFloat(total);
		}

		totalTemp = convert;
		descontos[4] = convert / valHdiasf();
	}

	public String getTotal() {
		return String.format("R$%.2f", totalTemp);
	}

	public String getShowMulta() {
		if (descontos[0] != 1.0f || descontos[1] != 1.0f) {
			return "inherit";
		} else {
			return "inherit";
		}
	}

	public String getHoriPrevis() {
		return "" + (aluguelSel.getTempo_hd() * getNdiasPrev() + aluguelSel.getHori_saida());
	}

	public void setHoriRetorno(int hori) {
		aluguelSel.setHori_retorno(hori);
	}

	public int getHoriRetorno() {
		return aluguelSel.getHori_retorno();
	}
	
	public boolean getDisabledReturn() {
		return aluguelSel.getData_entregue()!=null;
	}
	
	public boolean getDisabledPayment() {
		return aluguelSel.getValor_pago()>=aluguelSel.getVal_contratado();
	}
	
	public void setEntradaDinheiro(String total) {
		float convert;
		try {
			convert = Float.parseFloat(total);
		} catch (NumberFormatException e) {
			total = total.replaceAll("[^0-9,]", "");
			total = total.replaceAll(",", ".");
			convert = Float.parseFloat(total);
		}

		dinheiro = convert;
	}

	public String getEntradaDinheiro() {
		return String.format("R$%.2f", dinheiro);
	}
	
	public String getTroco() {
		float troco = dinheiro + aluguelSel.getValor_pago() - aluguelSel.getVal_contratado();
		System.out.println(troco);
		if(troco<0)
			troco = 0;
		return String.format("R$%.2f", troco);
	}

	// ----------------------Methods--------------------------------//
	public String getAluguel_id_param() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String aluguel_id = paramMap.get("aluguel_id");

		return aluguel_id;
	}

	public void updateMulta() {
		descontos[0] = getNdias() / (float) getNdiasPrev();
		if (descontos[0] < 1)
			descontos[0] = 1;
		descontos[1] = (aluguelSel.getHori_retorno()-aluguelSel.getHori_saida()) /(Float.parseFloat(getHoriPrevis())- aluguelSel.getHori_saida());
		if (descontos[1] < 1)
			descontos[1] = 1;
		descontos[2] = HUtil.descNdias(getNdias());
		descontos[3] = HUtil.descHorasDias(getAluguelSel().getTempo_hd());
		if (descontos[0] > 1 || descontos[1] > 1) {
			setTotal("" + getValor() * descontos[0] * descontos[1] * descontos[2] * descontos[3]);
		} else {
			setTotal("" + getAluguelSel().getVal_contratado());
		}
	}

	public String getForms(int i) {
		if (i == step) {
			return "inherit";
		} else {
			return "none";
		}
	}

	public void nextStep() {
		switch (step) {
		case 0:
			updateMulta();
			if(aluguelSel.getHori_retorno()>=aluguelSel.getHori_saida())
				step++;
			else {
				System.out.println("Preencha o horimetro com um valor válido.");
			}
			break;
		case 1:
			registrarRetorno();
			break;
		}
	}

	public void backStep() {
		if (step > 0)
			step--;
	}
	
	public void registrarRetorno() {
		aluguelSel.setVal_contratado(totalTemp);
		aluguelSel.setData_entregue(retorno);
		aluguelService.retorno(aluguelSel);
		System.out.println("Retorno registrado com sucesso!");
		SystemMB.getSystem().redirect("/p/aluguel/consultar.xhtml?aluguel_id="+aluguelSel.getN_aluguel());
	}
	public void payment() {
		if(dinheiro>0) {
			aluguelService.payment(aluguelSel, dinheiro);
			System.out.println("Pagamento registrado com sucesso!");
			SystemMB.getSystem().redirect("/p/aluguel/consultar.xhtml?aluguel_id="+aluguelSel.getN_aluguel());
		}else {
			System.out.println("Informe uma quantidade de dinheiro correta");
		}
	}

}
