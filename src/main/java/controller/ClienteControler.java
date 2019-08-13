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

import bean.ClienteBean;
import bean.EndClienteBean;
import bean.TelClienteBean;
import lookUp.ClienteLookUpList;
import model.ClienteModel;
import model.EndClienteModel;
import model.TelClienteModel;

@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteControler {
	private List<ClienteLookUpList> clientes;
	private List<ClienteLookUpList> filteredClientes;
	private List<EndClienteBean> enderecos;
	private List<TelClienteBean> tels;
	private ClienteLookUpList selectedCliente;
	private ClienteBean cliente;
	private TelClienteBean cliente_tel;
	private EndClienteBean cliente_end;
	private String cliente_id_param;
	private Boolean clienteEdicao;

	@ManagedProperty("#{clienteModel}")
	private ClienteModel clienteService;
	@ManagedProperty("#{endClienteModel}")
	private EndClienteModel endClienteService;
	@ManagedProperty("#{telClienteModel}")
	private TelClienteModel telClienteService;
	@ManagedProperty("#{message}")
	private MessagesMB messagesService;

	@PostConstruct
	public void init() {
		clientes = clienteService.list();
		cliente = null;
		cliente_id_param = null;
		clienteEdicao = true;
		cliente_end = null;
		cliente_tel = null;
		enderecos = null;
		tels = null;
	}

	public ClienteLookUpList getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(ClienteLookUpList selectedCliente) {
		this.selectedCliente = selectedCliente;
	}

	public List<ClienteLookUpList> getClientes() {
		return clientes;
	}

	public List<ClienteLookUpList> getFilteredClientes() {
		return filteredClientes;
	}

	public void setClienteService(ClienteModel clienteService) {
		this.clienteService = clienteService;
	}

	public ClienteModel getClienteService() {
		return clienteService;
	}

	public void setFilteredClientes(List<ClienteLookUpList> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}

	public MessagesMB getMessagesService() {
		return messagesService;
	}

	public void setMessagesService(MessagesMB messagesService) {
		this.messagesService = messagesService;
	}

	public Boolean getClienteEdicao() {
		return clienteEdicao;
	}

	public void setClienteEdicao(Boolean clienteEdicao) {
		this.clienteEdicao = clienteEdicao;
	}

	public TelClienteBean getCliente_tel() {
		if (cliente_tel == null)
			cliente_tel = new TelClienteBean();
		return cliente_tel;
	}

	public void setCliente_tel(TelClienteBean cliente_tel) {
		this.cliente_tel = cliente_tel;
	}

	public EndClienteBean getCliente_end() {
		if (cliente_end == null)
			cliente_end = new EndClienteBean();
		return cliente_end;
	}

	public void setCliente_end(EndClienteBean cliente_end) {
		this.cliente_end = cliente_end;
	}

	public String getCliente_id_param() {
		if (cliente_id_param == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
			cliente_id_param = paramMap.get("cliente_id");
		}

		return cliente_id_param;
	}

	public void setCliente_id_param(String cliente_id_param) {
		this.cliente_id_param = cliente_id_param;
	}

	public ClienteBean getCliente() {
		String param_id = getCliente_id_param();
		if (cliente == null) {
			if (param_id == null) {
				cliente = new ClienteBean();
				return cliente;
			}
			cliente = clienteService.read(getCliente_id_param());
		}
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public EndClienteModel getEndClienteService() {
		return endClienteService;
	}

	public void setEndClienteService(EndClienteModel endClienteService) {
		this.endClienteService = endClienteService;
	}

	public TelClienteModel getTelClienteService() {
		return telClienteService;
	}

	public void setTelClienteService(TelClienteModel telClienteService) {
		this.telClienteService = telClienteService;
	}

	public List<EndClienteBean> getEnderecos() {
		if (enderecos == null)
			enderecos = endClienteService.readEnderecos(getCliente_id_param());
		return enderecos;
	}

	public void setEnderecos(List<EndClienteBean> enderecos) {
		this.enderecos = enderecos;
	}

	public List<TelClienteBean> getTels() {
		if (tels == null)
			tels = telClienteService.readTels(getCliente_id_param());
		return tels;
	}

	public void setTels(List<TelClienteBean> tels) {
		this.tels = tels;
	}

	public void onEnderecoRowEdit(RowEditEvent event) {
		EndClienteBean end_edited = (EndClienteBean) event.getObject();
		end_edited.setEdited(true);
	}

	public void onEnderecoRowCancel(RowEditEvent event) {
		System.out.println((EndClienteBean) event.getObject());
	}

	public void onEnderecoAddNew() {
		enderecos.add(cliente_end);
		cliente_end = new EndClienteBean();
		PrimeFaces.current().executeScript("PF('dlg2').hide()");

		FacesMessage msg = new FacesMessage("Novo Endereço Adicionado.", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTelRowEdit(RowEditEvent event) {
		TelClienteBean tel_edited = (TelClienteBean) event.getObject();
		tel_edited.setEdited(true);
	}

	public void onTelRowCancel(RowEditEvent event) {
		System.out.println((TelClienteBean) event.getObject());
	}

	public void onTelAddNew() {
		this.tels.add(cliente_tel);
		cliente_tel = new TelClienteBean();
		PrimeFaces.current().executeScript("PF('dlg1').hide()");

		FacesMessage msg = new FacesMessage("Novo Número Adicionado.", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void createCliente() throws Exception {
		if (cliente != null) {
			if (clienteService.create(cliente)) {
				cliente_end.setN_cliente_fk(cliente.getN_cliente());
				cliente_tel.setN_cliente_fk(cliente.getN_cliente());
				if (endClienteService.create(cliente_end) && telClienteService.create(cliente_tel)) {
					messagesService.info("Cliente cadastrado com sucesso.");
					Thread.sleep(5000);
					SystemMB.getSystem().redirect("/p/cliente/listar.xhtml");
					return;
				}
			}
			messagesService.error("Error ao tentar cadastrar cliente.");
		}
	}

	public void salvarCliente() {
		for (EndClienteBean end : enderecos) {
			if (end.getN_end() == -1) {
				end.setN_cliente_fk(cliente.getN_cliente());
				endClienteService.create(end);
				continue;
			}
			if (end.isEdited())
				endClienteService.update(end);
		}

		for (TelClienteBean tel : tels) {
			if (tel.getN_telefone() == -1) {
				tel.setN_cliente_fk(cliente.getN_cliente());
				telClienteService.create(tel);
				continue;
			}
			if (tel.isEdited())
				telClienteService.update(tel);
		}

		clienteService.update(cliente);
	}

	public void excluirMaquina() {
		if (cliente != null) {
			clienteService.delete(getCliente_id_param());
			return;
		}
		System.out.println("Error: Cliente não foi instanciado.");
	}

	public void setClientCpf(String cpf) {
		cliente.setCpf(cpf.replaceAll("[^0-9]", ""));
	}

	public String getClientCpf() {
		String r = cliente.getCpf();
		if (r != null)
			return r;
//			return r.substring(0, 2) + "." + r.substring(3, 5) + "." + r.substring(6, 8) + "-" + r.substring(9, 11);
		return "";
	}

	public void setClientPhone(String phone) {
		phone = phone.replaceAll("[^0-9]", "");
		try {
			cliente_tel.setNumero_tel(Integer.parseInt(phone));
		} catch (NumberFormatException e) {

		}

	}

	public String getClientPhone() {
		return String.format("%d-%d", cliente_tel.getNumero_tel() / 10000, cliente_tel.getNumero_tel() % 10000);
	}

}
