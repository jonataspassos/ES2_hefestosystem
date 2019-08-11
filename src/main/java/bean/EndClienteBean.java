package bean;

import javax.faces.bean.ApplicationScoped;

public class EndClienteBean {
	private int n_end;
	private int n_cliente_fk;
	private String uf;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	private String cep;

	public int getN_end() {
		return n_end;
	}

	public void setN_end(int n_end) {
		this.n_end = n_end;
	}

	public int getN_cliente_fk() {
		return n_cliente_fk;
	}

	public void setN_cliente_fk(int n_cliente_fk) {
		this.n_cliente_fk = n_cliente_fk;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "EndClienteBean [n_end=" + n_end + ", n_cliente_fk=" + n_cliente_fk + ", uf=" + uf + ", cidade=" + cidade
				+ ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + ", cep=" + cep + "]";
	}
}
