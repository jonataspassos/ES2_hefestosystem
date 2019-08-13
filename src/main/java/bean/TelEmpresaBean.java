package bean;

public class TelEmpresaBean {
	private int n_telefone;
	private int n_empresa_fk;
	private int ddd;
	private int numero_tel;
	private boolean edited = false;

	public int getN_telefone() {
		return n_telefone;
	}

	public void setN_telefone(int n_telefone) {
		this.n_telefone = n_telefone;
	}

	public int getN_empresa_fk() {
		return n_empresa_fk;
	}

	public void setN_empresa_fk(int n_empresa_fk) {
		this.n_empresa_fk = n_empresa_fk;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero_tel() {
		return numero_tel;
	}

	public void setNumero_tel(int numero_tel) {
		this.numero_tel = numero_tel;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	@Override
	public String toString() {
		return "TelEmpresaBean [n_telefone=" + n_telefone + ", n_empresa_fk=" + n_empresa_fk + ", ddd=" + ddd
				+ ", numero_tel=" + numero_tel + ", edited=" + edited + "]";
	}

}
