package lookUp;

import java.io.Serializable;

public class EmpresaLookUpList implements Serializable{
	private int n_empresa;
	private String cnpj;
	private String raz_social;
	private String telefone;
	private int n_alugueis;
	public int getN_empresa() {
		return n_empresa;
	}
	public void setN_empresa(int n_empresa) {
		this.n_empresa = n_empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRaz_social() {
		return raz_social;
	}
	public void setRaz_social(String raz_social) {
		this.raz_social = raz_social;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getN_alugueis() {
		return n_alugueis;
	}
	public void setN_alugueis(int n_alugueis) {
		this.n_alugueis = n_alugueis;
	}
	
}
