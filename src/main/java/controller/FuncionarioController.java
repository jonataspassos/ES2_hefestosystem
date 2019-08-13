package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import bean.FuncionarioBean;
import bean.EndFuncionarioBean;
import bean.TelFuncionarioBean;
import lookUp.FuncionarioLookUp;
import model.FuncionarioModel;
import model.EndFuncionarioModel;
import model.TelFuncionarioModel;

@ManagedBean(name = "funcionarioMB")
@ViewScoped
public class FuncionarioController {
	private List<FuncionarioLookUp> funcionarios;
	private List<FuncionarioLookUp> filteredFuncionarios;
	private List<EndFuncionarioBean> enderecos;
	private List<TelFuncionarioBean> tels;
	private FuncionarioLookUp selectedFuncionario;
	private FuncionarioBean funcionario;
	private TelFuncionarioBean funcionario_tel;
	private EndFuncionarioBean funcionario_end;
	private String funcionario_id_param;
	private Boolean funcionarioEdicao;

	@ManagedProperty("#{funcionarioModel}")
	private FuncionarioModel funcionarioService;
	@ManagedProperty("#{endFuncionarioModel}")
	private EndFuncionarioModel endFuncionarioService;
	@ManagedProperty("#{telFuncionarioModel}")
	private TelFuncionarioModel telFuncionarioService;
	@ManagedProperty("#{message}")
	private MessagesMB messagesService;

	@PostConstruct
	public void init() {
		funcionarios = funcionarioService.list();
		funcionario = null;
		funcionario_id_param = null;
		funcionarioEdicao = true;
		funcionario_end = null;
		funcionario_tel = null;
		enderecos = null;
		tels = null;
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

	public List<FuncionarioLookUp> getFilteredFuncionarios() {
		return filteredFuncionarios;
	}

	public void setFuncionarioService(FuncionarioModel funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public FuncionarioModel getFuncionarioService() {
		return funcionarioService;
	}

	public void setFilteredFuncionarios(List<FuncionarioLookUp> filteredFuncionarios) {
		this.filteredFuncionarios = filteredFuncionarios;
	}

	public MessagesMB getMessagesService() {
		return messagesService;
	}

	public void setMessagesService(MessagesMB messagesService) {
		this.messagesService = messagesService;
	}

	public Boolean getFuncionarioEdicao() {
		return funcionarioEdicao;
	}

	public void setFuncionarioEdicao(Boolean funcionarioEdicao) {
		this.funcionarioEdicao = funcionarioEdicao;
	}

	public TelFuncionarioBean getFuncionario_tel() {
		if (funcionario_tel == null)
			funcionario_tel = new TelFuncionarioBean();
		return funcionario_tel;
	}

	public void setFuncionario_tel(TelFuncionarioBean funcionario_tel) {
		this.funcionario_tel = funcionario_tel;
	}

	public EndFuncionarioBean getFuncionario_end() {
		if (funcionario_end == null)
			funcionario_end = new EndFuncionarioBean();
		return funcionario_end;
	}

	public void setFuncionario_end(EndFuncionarioBean funcionario_end) {
		this.funcionario_end = funcionario_end;
	}

	public String getFuncionario_id_param() {
		if (funcionario_id_param == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
			funcionario_id_param = paramMap.get("funcionario_id");
		}

		return funcionario_id_param;
	}

	public void setFuncionario_id_param(String funcionario_id_param) {
		this.funcionario_id_param = funcionario_id_param;
	}

	public FuncionarioBean getFuncionario() {
		String param_id = getFuncionario_id_param();
		if (funcionario == null) {
			if (param_id == null) {
				funcionario = new FuncionarioBean();
				return funcionario;
			}
			funcionario = funcionarioService.read(getFuncionario_id_param());
		}
		return funcionario;
	}

	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}

	public EndFuncionarioModel getEndFuncionarioService() {
		return endFuncionarioService;
	}

	public void setEndFuncionarioService(EndFuncionarioModel endFuncionarioService) {
		this.endFuncionarioService = endFuncionarioService;
	}

	public TelFuncionarioModel getTelFuncionarioService() {
		return telFuncionarioService;
	}

	public void setTelFuncionarioService(TelFuncionarioModel telFuncionarioService) {
		this.telFuncionarioService = telFuncionarioService;
	}

	public List<EndFuncionarioBean> getEnderecos() {
		if (enderecos == null)
			enderecos = endFuncionarioService.readEnderecos(getFuncionario_id_param());
		return enderecos;
	}

	public void setEnderecos(List<EndFuncionarioBean> enderecos) {
		this.enderecos = enderecos;
	}

	public List<TelFuncionarioBean> getTels() {
		if (tels == null)
			tels = telFuncionarioService.readTels(getFuncionario_id_param());
		return tels;
	}

	public void setTels(List<TelFuncionarioBean> tels) {
		this.tels = tels;
	}

	public void onEnderecoRowEdit(RowEditEvent event) {
		EndFuncionarioBean end_edited = (EndFuncionarioBean) event.getObject();
		end_edited.setEdited(true);
	}

	public void onEnderecoRowCancel(RowEditEvent event) {
		EndFuncionarioBean end_edited = (EndFuncionarioBean) event.getObject();

		if (end_edited.getN_end() != -1)
			endFuncionarioService.delete(end_edited.getN_end());

		enderecos.remove(enderecos.indexOf(end_edited));

		PrimeFaces.current().ajax().update(":form3:enderecos");
	}

	public void onEnderecoAddNew() {
		enderecos.add(funcionario_end);
		funcionario_end = new EndFuncionarioBean();
		PrimeFaces.current().executeScript("PF('dlg2').hide()");

		FacesMessage msg = new FacesMessage("Novo Endereço Adicionado.", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTelRowEdit(RowEditEvent event) {
		TelFuncionarioBean tel_edited = (TelFuncionarioBean) event.getObject();
		tel_edited.setEdited(true);
	}

	public void onTelRowCancel(RowEditEvent event) {
		TelFuncionarioBean tel_edited = (TelFuncionarioBean) event.getObject();

		if (tel_edited.getN_telefone() != -1)
			telFuncionarioService.delete(tel_edited.getN_telefone());

		tels.remove(tels.indexOf(tel_edited));

		PrimeFaces.current().ajax().update(":form2:tels");
	}

	public void onTelAddNew() {
		this.tels.add(funcionario_tel);
		funcionario_tel = new TelFuncionarioBean();
		PrimeFaces.current().executeScript("PF('dlg1').hide()");

		FacesMessage msg = new FacesMessage("Novo Número Adicionado.", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void createFuncionario() throws Exception {
		if (funcionario != null) {
			if (funcionarioService.create(funcionario)) {
				funcionario_end.setN_funcionario_fk(funcionario.getN_funcionario());
				funcionario_tel.setN_funcionario_fk(funcionario.getN_funcionario());
				if (endFuncionarioService.create(funcionario_end) && telFuncionarioService.create(funcionario_tel)) {
					messagesService.info("Funcionario cadastrado com sucesso.");
					Thread.sleep(5000);
					SystemMB.getSystem().redirect("/p/funcionario/listar.xhtml");
					return;
				}
			}
			messagesService.error("Error ao tentar cadastrar funcionario.");
		}
	}

	public void salvarFuncionario() {
		for (EndFuncionarioBean end : enderecos) {
			if (end.getN_end() == -1) {
				end.setN_funcionario_fk(funcionario.getN_funcionario());
				endFuncionarioService.create(end);
				continue;
			}
			if (end.isEdited())
				endFuncionarioService.update(end);
		}

		for (TelFuncionarioBean tel : tels) {
			if (tel.getN_telefone() == -1) {
				tel.setN_funcionario_fk(funcionario.getN_funcionario());
				telFuncionarioService.create(tel);
				continue;
			}
			if (tel.isEdited())
				telFuncionarioService.update(tel);
		}

		funcionarioService.update(funcionario);
	}

	public void excluirTel() {
		for (TelFuncionarioBean tel : tels) {
			if (tel.getN_telefone() != -1) 
				telFuncionarioService.delete(tel.getN_telefone());
		}
	}

	public void excluirEndereco() {
		for (EndFuncionarioBean end : enderecos) {
			if (end.getN_end() != -1) 
				endFuncionarioService.delete(end.getN_end());
		}
	}

	public void excluirFuncionario() {
		if (funcionario != null) {
			if (enderecos.size() > 0)
				excluirEndereco();
			if (tels.size() > 0)
				excluirTel();

			funcionarioService.delete(funcionario.getN_funcionario());
			return;
		}
		System.out.println("Error: Funcionario não foi instanciado.");
	}

	public void setFuncCpf(String cpf) {
		funcionario.setCpf(cpf.replaceAll("[^0-9]", ""));
	}

	public String getFuncCpf() {
		String r = funcionario.getCpf();
		if (r != null)
			return r;
//			return r.substring(0, 2) + "." + r.substring(3, 5) + "." + r.substring(6, 8) + "-" + r.substring(9, 11);
		return "";
	}

	public void setFuncPhone(String phone) {
		phone = phone.replaceAll("[^0-9]", "");
		try {
			funcionario_tel.setNumero_tel(Integer.parseInt(phone));
		} catch (NumberFormatException e) {

		}

	}

	public String getFuncPhone() {
		return String.format("%d-%d", funcionario_tel.getNumero_tel() / 10000, funcionario_tel.getNumero_tel() % 10000);
	}

}
