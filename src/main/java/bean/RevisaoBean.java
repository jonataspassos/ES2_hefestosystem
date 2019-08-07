package bean;

import java.util.Date;

public class RevisaoBean 
{
	private int		n_revisao;
	private int		n_maquina_fk;
	private Date	data;
	private String	motivo;
	private int		horimetro;
	
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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

}
