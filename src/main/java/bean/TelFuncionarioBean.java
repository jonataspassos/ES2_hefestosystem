package bean;

public class TelFuncionarioBean {
	private int n_telefone = -1;
	private int n_funcionario_fk;
	private int ddd;
	private int numero_tel;
	private boolean edited = false;

	public int getN_telefone() {
		return n_telefone;
	}

	public void setN_telefone(int n_telefone) {
		this.n_telefone = n_telefone;
	}

	public int getN_funcionario_fk() {
		return n_funcionario_fk;
	}

	public void setN_funcionario_fk(int n_funcionario_fk) {
		this.n_funcionario_fk = n_funcionario_fk;
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
		return "TelFuncionarioBean [n_telefone=" + n_telefone + ", n_funcionario_fk=" + n_funcionario_fk + ", ddd="
				+ ddd + ", numero_tel=" + numero_tel + ", edited=" + edited + "]";
	}

}
