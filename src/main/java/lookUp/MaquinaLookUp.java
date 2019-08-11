package lookUp;

import java.io.Serializable;
import java.util.Date;

import bean.MaquinaBean;

public class MaquinaLookUp extends MaquinaBean implements Serializable{
	private Date data_ult_revisao;
	private float horimetro;
	private String status;

	public float getHorimetro() {
		return horimetro;
	}

	public void setHorimetro(float horimetro) {
		this.horimetro = horimetro;
	}

	public Date getData_ult_revisao() {
		return data_ult_revisao;
	}

	public void setData_ult_revisao(Date data_ult_revisao) {
		this.data_ult_revisao = data_ult_revisao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "" + getN_registro() + " - " + getMarca() + " - " + getPotencia() + "kVa";
	}
}
