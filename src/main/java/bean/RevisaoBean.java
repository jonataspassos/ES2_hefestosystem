package bean;

import java.time.LocalDate;

public class RevisaoBean {
	private int n_revisao;
	private int n_maquina_fk;
	private LocalDate data;
	private String motivo;
	private int horimetro;
	private LocalDate data_retorno;
	private int hori_retorno;

	public int getN_revisao() {
		return n_revisao;
	}

	public void setN_revisao(int n_revisao) {
		this.n_revisao = n_revisao;
	}

	public int getN_maquina_fk() {
		return n_maquina_fk;
	}

	public void setN_maquina_fk(int n_maquina_fk) {
		this.n_maquina_fk = n_maquina_fk;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate date) {
		this.data = date;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public int getHorimetro() {
		return horimetro;
	}

	public void setHorimetro(int horimetro) {
		this.horimetro = horimetro;
	}

	public LocalDate getData_retorno() {
		return data_retorno;
	}

	public void setData_retorno(LocalDate data_retorno) {
		this.data_retorno = data_retorno;
	}

	public int getHori_retorno() {
		return hori_retorno;
	}

	public void setHori_retorno(int hori_retorno) {
		this.hori_retorno = hori_retorno;
	}
	
	public static LocalDate dateFromString(String date) {
		String dataStr[] = new String[3];
		Integer dataInt[] = new Integer[3];

		dataStr = date.split("-");
		dataInt[0] = Integer.parseInt(dataStr[0]);
		dataInt[1] = Integer.parseInt(dataStr[1]);
		dataInt[2] = Integer.parseInt(dataStr[2]);
		
		return LocalDate.of(dataInt[0], dataInt[1], dataInt[2]);
	}

}
