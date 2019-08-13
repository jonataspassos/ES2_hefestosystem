package bean;

import javax.faces.bean.ApplicationScoped;

public class TelClienteBean {
	private int n_telefone = -1;
	private int n_cliente_fk;
	private int ddd;
	private int numero_tel;
	private boolean edited = false;

	public int getN_telefone() {
		return n_telefone;
	}

	public void setN_telefone(int n_telefone) {
		this.n_telefone = n_telefone;
	}

	public int getN_cliente_fk() {
		return n_cliente_fk;
	}

	public void setN_cliente_fk(int n_cliente_fk) {
		this.n_cliente_fk = n_cliente_fk;
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
		return "TelClienteBean [n_telefone=" + n_telefone + ", n_cliente_fk=" + n_cliente_fk + ", ddd=" + ddd
				+ ", numero_tel=" + numero_tel + ", edited=" + edited + "]";
	}

}
