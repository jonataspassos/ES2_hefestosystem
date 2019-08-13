package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import bean.AluguelBean;
import bean.RevisaoBean;
import model.MaquinaModel;
import lookUp.MaquinaLookUp;

import java.io.Serializable;
import java.time.LocalDate;

@ManagedBean(name = "maquina")
@ViewScoped
public class MaquinaController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{maquinaModel}")
	private MaquinaModel maqm;
	private MaquinaLookUp maquina;
	private List<MaquinaLookUp> maquinas;
	private String maquina_id_param;
	private ArrayList<AluguelBean> alugueis;
	private ArrayList<RevisaoBean> revisoes;
	private MaquinaLookUp selectedMaquina;
	private List<MaquinaLookUp> filteredMaquinas;
	private Boolean maquinaEdicao;
	private RevisaoBean revisao;

	@PostConstruct
	public void init() {
		maquina = null;
		revisao = null;
		alugueis = null;
		revisoes = null;
		maquina_id_param = null;
		maquinaEdicao = false;
		maquinas = null;
	}

	public MaquinaModel getMaqm() {
		return maqm;
	}

	public void setMaqm(MaquinaModel maqm) {
		this.maqm = maqm;
	}

	public MaquinaLookUp getMaquina() {
		String param_id = getMaquina_id_param();
		if (maquina == null) {
			if (param_id == null) {
				maquina = new MaquinaLookUp();
				return maquina;
			}
			maquina = maqm.read(getMaquina_id_param());
		}
		return maquina;
	}

	public void setMaquina(MaquinaLookUp maquina) {
		this.maquina = maquina;
	}

	public RevisaoBean getRevisao() {
		if (revisao == null)
			revisao = new RevisaoBean();
		return revisao;
	}

	public void setRevisao(RevisaoBean revisao) {
		this.revisao = revisao;
	}

	public String getMaquina_id_param() {
		if (maquina_id_param == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
			maquina_id_param = paramMap.get("maquina_id");
		}

		return maquina_id_param;
	}

	public void setMaquina_id_param(String maquina_id) {
		this.maquina_id_param = maquina_id;
	}

	public ArrayList<AluguelBean> getAlugueis() {
		if (alugueis == null)
			alugueis = maqm.getLastAlugueis(getMaquina_id_param());
		return alugueis;
	}

	public ArrayList<RevisaoBean> getRevisoes() {
		if (revisoes == null)
			revisoes = maqm.getLastRevisoes(getMaquina_id_param());
		return revisoes;
	}

	public void setRevisoes(ArrayList<RevisaoBean> revisoes) {
		this.revisoes = revisoes;
	}

	public Boolean getMaquinaEdicao() {
		return maquinaEdicao;
	}

	public void setMaquinaEdicao(Boolean maquinaEdicao) {
		this.maquinaEdicao = maquinaEdicao;
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
		if (maquinas == null)
			maquinas = maqm.list();
		return maquinas;
	}

	public void openDialog(Integer option) {
		switch (option) {
		case 0:
			PrimeFaces.current().executeScript("PF('dlgPorRevisao').show()");
			break;
		case 2:
			PrimeFaces.current().executeScript("PF('dlgTirarRevisao').show()");
		}
	}

	public void createMaquina() throws Exception {
		if (maqm.create(maquina)) {
			PrimeFaces.current().executeScript(
					"Swal.fire({ type: 'success', title: 'Tudo certo...', text: 'Máquina cadastrada.', timer: 4000,"
							+ "showConfirmButton: false})");
			System.out.println("Máquina cadastrada com sucesso.");
			maquina = new MaquinaLookUp();
			Thread.sleep(5000);
			SystemMB.getSystem().redirect("/p/maquina/listar.xhtml");
			return;
		}
		System.out.println("Error ao tentar cadastrar máquina.");
		PrimeFaces.current().executeScript(
				"Swal.fire({ type: 'error', title: 'Oopss...', text: 'Error ao tentar cadastrar máquina.'})");
	}

	public void putRevisao() {
		LocalDate date = LocalDate.now();
		if (revisao != null) {
			revisao.setData(date);
			revisao.setN_maquina_fk(Integer.parseInt(getMaquina_id_param()));
			maqm.createRevisao(revisao);
			return;
		}

		System.out.println("Error: Revisão não foi instanciada.");
		PrimeFaces.current().executeScript(
				"Swal.fire({ type: 'error', title: 'Oopss...', text: 'Erro de instância.'})");
	}

	public void cancelRevisao() {
		LocalDate date = LocalDate.now();
		if (revisao != null) {
			revisao.setData_retorno(date);
			maqm.cancelRevisao(revisao);
			return;
		}
	}

	public void salvarMaquina() {
		if (maquina != null) {
			maqm.update(maquina);
			maquinaEdicao = false;
			PrimeFaces.current().executeScript(
					"Swal.fire({ type: 'success', title: 'Tudo certo...', text: 'Máquina atualizada.', timer: 4000})");
			return;
		}

		System.out.println("Error: Máquina não foi instanciada.");
		PrimeFaces.current().executeScript(
				"Swal.fire({ type: 'error', title: 'Oopss...', text: 'Erro de instância.'})");
	}

	public void excluirMaquina() {
		if (maquina != null) {
			maqm.delete(getMaquina_id_param());
			PrimeFaces.current().executeScript(
					"Swal.fire({ type: 'success', title: 'Tudo certo...', text: 'Máquina excluída.', timer: 4000})");
			return;
		}
		System.out.println("Error: Máquina não foi instanciada.");
		PrimeFaces.current().executeScript(
				"Swal.fire({ type: 'error', title: 'Oopss...', text: 'Erro de instância.'})");
		
	}
	
	public void setMaqPotencia(Double potencia) {
		System.out.println(potencia);
		maquina.setPotencia(potencia.floatValue());
	}
	
	public Double getMaqPotencia() {
		return new Double(maquina.getPotencia());
	}
	
	public void setMaqDiaria(Double potencia) {
		System.out.println(potencia);
		maquina.setValor_diaria(potencia.floatValue());
	}
	public Double getMaqDiaria() {
		return new Double(maquina.getValor_diaria());
	}

}
