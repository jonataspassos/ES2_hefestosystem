package controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	private MaquinaBean maquina;
	private ArrayList<MaquinaLookUp> maquinas;
	private Integer maquina_id_param;
	private ArrayList<AluguelBean> alugueis;

	@PostConstruct
	public void init() {
//		maqm = new MaquinaModel();
		maquina = new MaquinaBean();
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

	public MaquinaBean getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaBean maquina) {
		this.maquina = maquina;
	}

	public ArrayList<MaquinaLookUp> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<MaquinaLookUp> maquinas) {
		this.maquinas = maquinas;
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

	public void createMaquina() throws Exception {
		if (maqm.create(maquina)) {
			messagesService.info("Máquina cadastrada com sucesso.");
			Thread.sleep(5000);
			SystemMB.getSystem().redirect("/p/maquina/listar.xhtml");
			return;
		}
		messagesService.error("Error ao tentar cadastrar máquina.");
	}

}
