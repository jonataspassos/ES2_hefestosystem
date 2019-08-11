package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bean.ClienteBean;
import bean.EndClienteBean;
import bean.TelClienteBean;
import lookUp.ClienteLookUpList;
import model.ClienteModel;

@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteControler {
	private List<ClienteLookUpList> clientes;
	private List<ClienteLookUpList> filteredClientes;
	private ClienteLookUpList selectedCliente;
	private ClienteBean cliente;
	private TelClienteBean cliente_tel;
	private EndClienteBean cliente_end;
	private String cliente_id_param;
	private Boolean clienteEdicao;

	@ManagedProperty("#{clienteModel}")
	private ClienteModel clienteService;
	@ManagedProperty("#{message}")
	private MessagesMB messagesService;

	@PostConstruct
	public void init() {
		clientes = clienteService.list();
		cliente = null;
		cliente_id_param = null;
		clienteEdicao = false;
		cliente_end = null;
		cliente_tel = null;
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

	public void createCliente() throws Exception {
		if (cliente != null) {
			System.out.println(cliente);
			System.out.println(cliente_end);
			System.out.println(cliente_tel);
//			if (clienteService.create(cliente)) {
//				messagesService.info("Cliente cadastrado com sucesso.");
//				Thread.sleep(5000);
//				SystemMB.getSystem().redirect("/p/cliente/listar.xhtml");
//				return;
//			}
//			messagesService.error("Error ao tentar cadastrar cliente.");
		}
	}

	public void salvarCliente() {
		if (cliente != null) {
			clienteService.update(cliente);
			return;
		}

		System.out.println("Error: Cliente não foi instanciado.");
	}

	public void excluirMaquina() {
		if (cliente != null) {
			clienteService.delete(getCliente_id_param());
			return;
		}
		System.out.println("Error: Cliente não foi instanciado.");
	}

}
