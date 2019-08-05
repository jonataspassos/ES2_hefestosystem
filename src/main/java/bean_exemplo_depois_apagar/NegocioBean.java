package bean_exemplo_depois_apagar;

import java.util.Date;

public class NegocioBean {
	private int us_vendedor;
	private int us_comprador;
	private Date data;
	private float valor;
	public int getUs_vendedor() {
		return us_vendedor;
	}
	public void setUs_vendedor(int us_vendedor) {
		this.us_vendedor = us_vendedor;
	}
	public int getUs_comprador() {
		return us_comprador;
	}
	public void setUs_comprador(int us_comprador) {
		this.us_comprador = us_comprador;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}


}
