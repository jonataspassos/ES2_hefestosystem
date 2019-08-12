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
		FacesMessage msg = new FacesMessage("Endereço Editado.", ((EndClienteBean) event.getObject()).getRua());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onEnderecoRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada.", ((EndClienteBean) event.getObject()).getRua());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onEnderecoAddNew() {
		System.out.println("TESTE");
		System.out.println(cliente_end);
		
		enderecos.add(cliente_end);
		
		FacesMessage msg = new FacesMessage("Novo Endereço Adicionado.", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onEnderecoCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cédula modificada",
					"Velha: " + oldValue + ", Nova:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onTelRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Telefone Editado.", "" + ((TelClienteBean) event.getObject()).getNumero_tel());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTelRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada.", "" + ((TelClienteBean) event.getObject()).getNumero_tel());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTelAddNew() {
		System.out.println("TESTE");
		System.out.println(cliente_tel);
		
		System.out.println(tels.size());
		this.tels.add(cliente_tel);
		System.out.println(tels.size());
		
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
		System.out.println(getCliente_end());
		System.out.println(cliente);
//		if (cliente != null) {
//			clienteService.update(cliente);
//			return;
//		}

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error.",
				"Error: Cliente não foi instanciado.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
