package lookUp;

import java.io.Serializable;

public class FuncionarioLookUp implements Serializable{
	private int n_funcionario;
	private String cpf;
	private String nome;
	private String telefone;
	public int getN_funcionario() {
		return n_funcionario;
	}
	public void setN_funcionario(int n_funcionario) {
		this.n_funcionario = n_funcionario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "" + cpf + " - " + nome + " - "
				+ telefone;
	}
	
}
