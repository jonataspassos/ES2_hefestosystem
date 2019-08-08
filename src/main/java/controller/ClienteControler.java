package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lookUp.ClienteLookUpList;
import model.ClienteModel;

@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteControler implements Serializable {
	private List<ClienteLookUpList> clientes;
	private List<ClienteLookUpList> filteredClientes;
	private ClienteLookUpList selectedCliente;

	@ManagedProperty("#{clienteModel}")
	private ClienteModel clienteService;
	@ManagedProperty("#{message}")
	private MessagesMB messageService;

	@PostConstruct
	public void init() {
		clientes = clienteService.list();
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

	public MessagesMB getMessageService() {
		return messageService;
	}

	public void setMessageService(MessagesMB messageService) {
		this.messageService = messageService;
	}
	
}
