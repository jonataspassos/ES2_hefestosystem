package bean;

public class EndEmpresaBean {
	private int n_end = -1;
	private int n_empresa_fk;
	private String uf;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	private String cep;
	private boolean edited = false;

	public int getN_end() {
		return n_end;
	}

	public void setN_end(int n_end) {
		this.n_end = n_end;
	}

	public int getN_empresa_fk() {
		return n_empresa_fk;
	}

	public void setN_empresa_fk(int n_empresa_fk) {
		this.n_empresa_fk = n_empresa_fk;
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

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	@Override
	public String toString() {
		return "EndEmpresaBean [n_end=" + n_end + ", n_empresa_fk=" + n_empresa_fk + ", uf=" + uf + ", cidade=" + cidade
				+ ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + ", cep=" + cep + ", edited=" + edited
				+ "]";
	}

}
