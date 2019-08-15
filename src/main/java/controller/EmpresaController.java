package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import bean.EmpresaBean;
import bean.EndEmpresaBean;
import bean.TelEmpresaBean;
import lookUp.EmpresaLookUpList;
import model.EmpresaModel;
import model.EndEmpresaModel;
import model.TelEmpresaModel;

@ManagedBean(name = "empresaMB")
@ViewScoped
public class EmpresaController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<EmpresaLookUpList> empresas;
	private List<EmpresaLookUpList> filteredEmpresas;
	private EmpresaLookUpList selectedEmpresa;
	private List<EndEmpresaBean> enderecos;
	private List<TelEmpresaBean> tels;
	private EmpresaBean empresa;
	private TelEmpresaBean empresa_tel;
	private EndEmpresaBean empresa_end;
	private String empresa_id_param;
	private Boolean empresaEdicao;

	@ManagedProperty("#{empresaModel}")
	private EmpresaModel empresaService;
	@ManagedProperty("#{endEmpresaModel}")
	private EndEmpresaModel endEmpresaService;
	@ManagedProperty("#{telEmpresaModel}")
	private TelEmpresaModel telEmpresaService;

	@PostConstruct
	public void init() {
		empresas = empresaService.list();
		empresa = null;
		empresa_id_param = null;
		empresaEdicao = true;
		empresa_end = null;
		empresa_tel = null;
		enderecos = null;
		tels = null;
	}

	public List<EmpresaLookUpList> getFilteredEmpresas() {
		return filteredEmpresas;
	}

	public void setFilteredEmpresas(List<EmpresaLookUpList> filteredEmpresas) {
		this.filteredEmpresas = filteredEmpresas;
	}

	public EmpresaLookUpList getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public void setSelectedEmpresa(EmpresaLookUpList selectedEmpresa) {
		this.selectedEmpresa = selectedEmpresa;
	}

	public List<EmpresaLookUpList> getEmpresas() {
		return empresas;
	}

	public void setEmpresaService(EmpresaModel empresaService) {
		this.empresaService = empresaService;
	}
	
	public EmpresaBean getEmpresa() {		
		String param_id = getEmpresa_id_param();
		if (empresa == null) {
			if (param_id == null) {
				empresa = new EmpresaBean();
				return empresa;
			}
			empresa = empresaService.readBean(Integer.parseInt(getEmpresa_id_param()));
		}
		return empresa;
	}

	public void setEmpresa(EmpresaBean empresa) {
		this.empresa = empresa;
	}

	public TelEmpresaBean getEmpresa_tel() {
		if(empresa_tel == null)
			empresa_tel = new TelEmpresaBean();
		return empresa_tel;
	}

	public void setEmpresa_tel(TelEmpresaBean empresa_tel) {
		this.empresa_tel = empresa_tel;
	}

	public EndEmpresaBean getEmpresa_end() {
		if (empresa_end == null)
			empresa_end = new EndEmpresaBean();
		return empresa_end;
	}

	public void setEmpresa_end(EndEmpresaBean empresa_end) {
		this.empresa_end = empresa_end;
	}

	public Boolean getEmpresaEdicao() {
		return empresaEdicao;
	}

	public void setEmpresaEdicao(Boolean empresaEdicao) {
		this.empresaEdicao = empresaEdicao;
	}

	public EndEmpresaModel getEndEmpresaService() {
		return endEmpresaService;
	}

	public void setEndEmpresaService(EndEmpresaModel endEmpresaService) {
		this.endEmpresaService = endEmpresaService;
	}

	public TelEmpresaModel getTelEmpresaService() {
		return telEmpresaService;
	}

	public void setTelEmpresaService(TelEmpresaModel telEmpresaService) {
		this.telEmpresaService = telEmpresaService;
	}

	public EmpresaModel getEmpresaService() {
		return empresaService;
	}

	public void setEmpresas(List<EmpresaLookUpList> empresas) {
		this.empresas = empresas;
	}

	public String getEmpresa_id_param() {
		if (empresa_id_param == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
			empresa_id_param = paramMap.get("empresa_id");
		}

		return empresa_id_param;
	}

	public void setEmpresa_id_param(String empresa_id_param) {
		this.empresa_id_param = empresa_id_param;
	}

	public List<EndEmpresaBean> getEnderecos() {
		if (enderecos == null)
			enderecos = endEmpresaService.readEnderecos(getEmpresa_id_param());
		return enderecos;
	}

	public void setEnderecos(List<EndEmpresaBean> enderecos) {
		this.enderecos = enderecos;
	}

	public List<TelEmpresaBean> getTels() {
		if (tels == null)
			tels = telEmpresaService.readTels(getEmpresa_id_param());
		return tels;
	}

	public void setTels(List<TelEmpresaBean> tels) {
		this.tels = tels;
	}

	public void onEnderecoRowEdit(RowEditEvent event) {
		EndEmpresaBean end_edited = (EndEmpresaBean) event.getObject();
		end_edited.setEdited(true);
	}

	public void onEnderecoRowCancel(RowEditEvent event) {
		EndEmpresaBean end_edited = (EndEmpresaBean) event.getObject();

		if (end_edited.getN_end() != -1)
			endEmpresaService.delete(end_edited.getN_end());

		enderecos.remove(enderecos.indexOf(end_edited));

		PrimeFaces.current().ajax().update(":form3:enderecos");
	}

	public void onEnderecoAddNew() {
		enderecos.add(empresa_end);
		empresa_end = new EndEmpresaBean();
		PrimeFaces.current().executeScript("PF('dlg2').hide()");

		System.out.println("Novo Endereço Adicionado.");
	}

	public void onTelRowEdit(RowEditEvent event) {
		TelEmpresaBean tel_edited = (TelEmpresaBean) event.getObject();
		tel_edited.setEdited(true);
	}

	public void onTelRowCancel(RowEditEvent event) {
		TelEmpresaBean tel_edited = (TelEmpresaBean) event.getObject();

		if (tel_edited.getN_telefone() != -1)
			telEmpresaService.delete(tel_edited.getN_telefone());

		tels.remove(tels.indexOf(tel_edited));
		tels.add(tel_edited);
		PrimeFaces.current().ajax().update("tels");
		tels.remove(tels.indexOf(tel_edited));
		PrimeFaces.current().ajax().update("tels");
	}

	public void onTelAddNew() {
		System.out.println(empresa_tel);
		System.out.println(tels.size());
		this.tels.add(empresa_tel);
		System.out.println(tels.size());
		empresa_tel = new TelEmpresaBean();
		PrimeFaces.current().executeScript("PF('dlg1').hide()");

		System.out.println("Novo Número Adicionado.");
	}

	public void createEmpresa() throws Exception {
		if (empresa != null) {
			if (empresaService.create(empresa)) {
				empresa_end.setN_empresa_fk(empresa.getN_empresa());
				empresa_tel.setN_empresa_fk(empresa.getN_empresa());
				if (endEmpresaService.create(empresa_end) && telEmpresaService.create(empresa_tel)) {
					System.out.println("Empresa cadastrada com sucesso.");
					PrimeFaces.current().executeScript(
							"Swal.fire({ type: 'success', title: 'Tudo certo...', text: 'Empresa cadastrada com sucesso.', timer: 4000,"
									+ "showConfirmButton: false})");
					Thread.sleep(5000);
					SystemMB.getSystem().redirect("/p/empresa/listar.xhtml");
					return;
				}
			}
			System.out.println("Error ao tentar cadastrar empresa.");
			PrimeFaces.current().executeScript(
					"Swal.fire({ type: 'error', title: 'Oopss...', text: 'Impossível cadastrar empresa.'})");
		}
	}

	public void salvarEmpresa() {
		for (EndEmpresaBean end : enderecos) {
			if (end.getN_end() == -1) {
				end.setN_empresa_fk(empresa.getN_empresa());
				endEmpresaService.create(end);
				continue;
			}
			if (end.isEdited())
				endEmpresaService.update(end);
		}

		for (TelEmpresaBean tel : tels) {
			if (tel.getN_telefone() == -1) {
				tel.setN_empresa_fk(empresa.getN_empresa());
				telEmpresaService.create(tel);
				continue;
			}
			if (tel.isEdited())
				telEmpresaService.update(tel);
		}

		empresaService.update(empresa);
		PrimeFaces.current().executeScript(
				"Swal.fire({ type: 'success', title: 'Tudo certo...', text: 'Empresa atualizada.', timer: 4000})");
	}

	public void excluirTel() {
		for (TelEmpresaBean tel : tels) {
			if (tel.getN_telefone() != -1)
				telEmpresaService.delete(tel.getN_telefone());
		}
	}

	public void excluirEndereco() {
		for (EndEmpresaBean end : enderecos) {
			if (end.getN_end() != -1)
				endEmpresaService.delete(end.getN_end());
		}
	}

	public void excluirEmpresa() {
		if (empresa != null) {
			if (enderecos.size() > 0)
				excluirEndereco();
			if (tels.size() > 0)
				excluirTel();

			empresaService.delete(empresa.getN_empresa());
			PrimeFaces.current().executeScript(
					"Swal.fire({ type: 'success', title: 'Tudo certo...', text: 'Empresa excluída.', timer: 4000})");
			return;
		}
		System.out.println("Error: Empresa não foi instanciada.");
	}

	public void setEmpresCnpj(String cnpj) {
		empresa.setCnpj(cnpj.replaceAll("[^0-9]", ""));
	}

	public String getEmpresCnpj() {
		String r = empresa.getCnpj();
		if (r != null)
			return r;
//			return r.substring(0, 2) + "." + r.substring(3, 5) + "." + r.substring(6, 8) + "-" + r.substring(9, 11);
		return "";
	}

	public void setEmpresPhone(String phone) {
		phone = phone.replaceAll("[^0-9]", "");
		try {
			empresa_tel.setNumero_tel(Integer.parseInt(phone));
		} catch (NumberFormatException e) {

		}

	}

	public String getEmpresPhone() {
		return String.format("%d-%d", empresa_tel.getNumero_tel() / 10000, empresa_tel.getNumero_tel() % 10000);
	}

}
