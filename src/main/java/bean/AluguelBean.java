package bean;

import java.util.Date;

public class AluguelBean 
{
	private int		n_aluguel;
	private int		n_cliente_fk;
	private int		n_funcionario_fk;
	private int		n_maquina_fk;
	private int		n_empresa_fk;
	private Date 	data_ini;
	private Date 	data_final;
	private Date 	data_entregue;
	private float 	valor_pago;
	private int 	hori_retorno;
	private int 	hori_saida;
	private float	val_contratado;
	private float	tempo_hd;
	public int getN_aluguel() {
		return n_aluguel;
	}
	public void setN_aluguel(int n_aluguel) {
		this.n_aluguel = n_aluguel;
	}
	public int getN_cliente_fk() {
		return n_cliente_fk;
	}
	public void setN_cliente_fk(int n_cliente_fk) {
		this.n_cliente_fk = n_cliente_fk;
	}
	public int getN_funcionario_fk() {
		return n_funcionario_fk;
	}
	public void setN_funcionario_fk(int n_funcionario_fk) {
		this.n_funcionario_fk = n_funcionario_fk;
	}
	public int getN_maquina_fk() {
		return n_maquina_fk;
	}
	public void setN_maquina_fk(int n_maquina_fk) {
		this.n_maquina_fk = n_maquina_fk;
	}
	public int getN_empresa_fk() {
		return n_empresa_fk;
	}
	public void setN_empresa_fk(int n_empresa_fk) {
		this.n_empresa_fk = n_empresa_fk;
	}
	public Date getData_ini() {
		return data_ini;
	}
	public void setData_ini(Date data_ini) {
		this.data_ini = data_ini;
	}
	public Date getData_final() {
		return data_final;
	}
	public void setData_final(Date data_final) {
		this.data_final = data_final;
	}
	public Date getData_entregue() {
		return data_entregue;
	}
	public void setData_entregue(Date data_entregue) {
		this.data_entregue = data_entregue;
	}
	public float getValor_pago() {
		return valor_pago;
	}
	public void setValor_pago(float valor_pago) {
		this.valor_pago = valor_pago;
	}
	public int getHori_retorno() {
		return hori_retorno;
	}
	public void setHori_retorno(int hori_retorno) {
		this.hori_retorno = hori_retorno;
	}
	public int getHori_saida() {
		return hori_saida;
	}
	public void setHori_saida(int hori_saida) {
		this.hori_saida = hori_saida;
	}
	public float getVal_contratado() {
		return val_contratado;
	}
	public void setVal_contratado(float val_contratado) {
		this.val_contratado = val_contratado;
	}
	public float getTempo_hd() {
		return tempo_hd;
	}
	public void setTempo_hd(float tempo_hd) {
		this.tempo_hd = tempo_hd;
	}
	

}
