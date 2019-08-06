package bean;

public class MaquinaBean {
	private int n_registro;
	private String marca;
	private float potencia;
	private float consumo;
	private float valor_diaria;
	private String tipo_combustivel;
	
	public int getN_registro() {
		return n_registro;
	}
	public void setN_registro(int n_registro) {
		this.n_registro = n_registro;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public float getPotencia() {
		return potencia;
	}
	public void setPotencia(float potencia) {
		this.potencia = potencia;
	}
	public float getConsumo() {
		return consumo;
	}
	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}
	public float getValor_diaria() {
		return valor_diaria;
	}
	public void setValor_diaria(float valor_diaria) {
		this.valor_diaria = valor_diaria;
	}
	public String getTipo_combustivel() {
		return tipo_combustivel;
	}
	public void setTipo_combustivel(String tipo_combustivel) {
		this.tipo_combustivel = tipo_combustivel;
	}
	@Override
	public String toString() {
		return "";
	}

}
