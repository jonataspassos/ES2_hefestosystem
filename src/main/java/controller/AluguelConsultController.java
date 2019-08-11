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
import lookUp.ClienteLookUpList;
import lookUp.EmpresaLookUpList;
import lookUp.MaquinaLookUp;
import model.AluguelModel;
import model.ClienteModel;
import model.EmpresaModel;
import model.MaquinaModel;

@ViewScoped
@ManagedBean(name = "aluguelConsultMB")
public class AluguelConsultController implements Serializable{

	// ------------------Select Beans------------------------------//
	private AluguelBean aluguelSel;

	private ClienteLookUpList clienteSel;
	private EmpresaLookUpList empresaSel;
	private MaquinaLookUp maquinaSel;

	// ----------------------Atributes-----------------------------//
	private float[] descontos = { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f };
	//atraso, abuso, n_dias, horas, personalizado
	private Date retorno;

	// ------------------Managed Propetys -------------------------//
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
		String param = getAluguel_id_param();
		if(param != null) {
			aluguelSel = aluguelService.readBean(Integer.parseInt(param));
			if(aluguelSel != null) {
				clienteSel = clienteService.readLookUp(aluguelSel.getN_cliente_fk());
				maquinaSel = maquinaService.read(""+aluguelSel.getN_maquina_fk());
				if(aluguelSel.getN_empresa_fk()!=-1) {
					empresaSel = new EmpresaLookUpList();
				}
				return;
			}			
		}//else
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

	public void setMessageService(MessagesMB messageService) {
		this.messageService = messageService;
	}
	//-----------------------Beans get Sets ----------------------//

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
	//--------------------------Get Set ---------------------------//
	public Date getRetorno() {
		if(retorno == null)
			retorno = new Date();
		return retorno;
	}

	public void setRetorno(Date retorno) {
		this.retorno = retorno;
	}

	//----------------------Unreall Get Set -----------------------//
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
		total = total.replaceAll("[^0-9,]", "");
		total = total.replaceAll(",", ".");
		getAluguelSel().setVal_contratado(Float.parseFloat(total));
		descontos[4] = aluguelSel.getVal_contratado() / valHdiasf();
	}

	public String getTotal() {
		return String.format("R$%.2f", aluguelSel.getVal_contratado());
	}
	public String getShowMulta() {
		if(descontos[0] != 1.0f || descontos[1] != 1.0f) {
			return "inherit";
		}else {
			return "inherit";
		}
	}
	public String getHoriPrevis() {
		return ""+ (aluguelSel.getTempo_hd()*getNdias() + aluguelSel.getHori_saida());
	}
	
	public void setHoriRetorno(int hori) {
		aluguelSel.setHori_retorno(hori);
	}
	public int getHoriRetorno() {
		return aluguelSel.getHori_retorno();
	}
	//----------------------Methods--------------------------------//
	public String getAluguel_id_param() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String aluguel_id = paramMap.get("aluguel_id");

		return aluguel_id;
	}
	
	public void openDialog(Integer option) {
		switch (option) {
		case 0:
			PrimeFaces.current().executeScript("PF('dlgReturn').show()");
			break;
		case 2:
			PrimeFaces.current().executeScript("PF('dlgTirarRevisao').show()");
		}
	}
	
	public void updateMulta() {
		System.out.println(getNdias());
		System.out.println(getNdiasPrev());
		descontos[0]= getNdias()/getNdiasPrev();
		PrimeFaces.current().executeScript("PF('dlgReturn').show()");
	}
	
}
