package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeApplicationContext;

import controller.MessagesMB;
import bean.AluguelBean;
import bean.MaquinaBean;
//import lookUp.UsuarioLookUpList;
import model.MaquinaModel;
import lookUp.MaquinaLookUp;

@ManagedBean(name = "maquina")
@ViewScoped
public class MaquinaController {
	@ManagedProperty("#{maquinaModel}")
	private MaquinaModel maqm;
	@ManagedProperty("#{message}")
	private MessagesMB messagesService;
	private MaquinaLookUp maquina;
	private List<MaquinaLookUp> maquinas;
	private Integer maquina_id_param;
	private ArrayList<AluguelBean> alugueis;
	private MaquinaLookUp selectedMaquina;
	private List<MaquinaLookUp> filteredMaquinas;
	private Boolean maquinaEdicao = false;

	@PostConstruct
	public void init() {
//		maqm = new MaquinaModel();
		maquina = null;
		maquinas = maqm.list();
	}

	public MessagesMB getMessagesService() {
		return messagesService;
	}

	public void setMessagesService(MessagesMB messagesService) {
		this.messagesService = messagesService;
	}

	public MaquinaModel getMaqm() {
		return maqm;
	}

	public void setMaqm(MaquinaModel maqm) {
		this.maqm = maqm;
	}

	public MaquinaLookUp getMaquina() {
		if (maquina == null)
			maquina = maqm.read(getMaquina_id_param());
		return maquina;
	}

	public void setMaquina(MaquinaLookUp maquina) {
		this.maquina = maquina;
	}

	public String getMaquina_id_param() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String maquina_id = paramMap.get("maquina_id");

		return maquina_id;
	}

	public void setMaquina_id_param(Integer maquina_id) {
		this.maquina_id_param = maquina_id;
	}

	public ArrayList<AluguelBean> getAlugueis() {
		return maqm.getLastAlugueis(this.getMaquina_id_param());
	}

	public Boolean getMaquinaEdicao() {
		return maquinaEdicao;
	}

	public void setMaquinaEdicao(Boolean maquinaEdicao) {
		this.maquinaEdicao = maquinaEdicao;
	}

	public void createMaquina() throws Exception {
		if (maqm.create(maquina)) {
			messagesService.info("Máquina cadastrada com sucesso.");
			Thread.sleep(5000);
			SystemMB.getSystem().redirect("/p/maquina/listar.xhtml");
			return;
		}
		messagesService.error("Error ao tentar cadastrar m�quina.");
	}

	public List<String> getMarcas() {
		return maqm.marcas();
	}

	public List<String> getTipo_combust() {
		return maqm.tipo_combust();
	}

	public MaquinaLookUp getSelectedMaquina() {
		return selectedMaquina;
	}

	public void setSelectedMaquina(MaquinaLookUp selectedMaquina) {
		this.selectedMaquina = selectedMaquina;
	}

	public List<MaquinaLookUp> getFilteredMaquinas() {
		return filteredMaquinas;
	}

	public void setFilteredMaquinas(List<MaquinaLookUp> filteredMaquinas) {
		this.filteredMaquinas = filteredMaquinas;
	}

	public List<MaquinaLookUp> getMaquinas() {
		return maquinas;
	}

	public void openDialog(Integer option) {
		switch (option) {
		case 1:
			System.out.println("entrou aqui 1");
//			PrimeFaces.current().executeScript("PF('dlgTirarRevisao').show()");
			break;
		case 2:
			System.out.println("entrou aqui 2");
//			PrimeFaces.current().executeScript("PF('dlgPorRevisao').show()");
		}
	}

}
