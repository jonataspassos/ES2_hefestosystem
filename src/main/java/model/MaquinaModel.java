package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.AluguelBean;
import bean.MaquinaBean;
import bean.RevisaoBean;
import lookUp.MaquinaLookUp;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class MaquinaModel implements Serializable {

	public Boolean create(MaquinaLookUp maquina) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE MAQUINA_CREATE(?,?,?,?,?)");

				NumberFormat df = new DecimalFormat("#0.00");

				st.setInt(1, maquina.getN_registro());
				st.setString(2, maquina.getMarca());
				st.setFloat(3, Float.parseFloat(df.format(maquina.getPotencia())));
				st.setFloat(4, Float.parseFloat(df.format(maquina.getValor_diaria())));
				st.setString(5, maquina.getTipo_combustivel());

				st.execute();

				st.close();
				conn.close();

				System.out.println("Máquina criada!!");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error com a criação de máquina!!");
			return false;
		}
		return false;
	}

	public MaquinaLookUp read(String maquina_id) {
		MaquinaLookUp maquina;
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("SELECT * FROM MAQUINA_LIST WHERE N_MAQUINA = ?");

				st.setString(1, maquina_id);

				ResultSet rs = st.executeQuery();

				if (rs.next()) {
					maquina = new MaquinaLookUp();

					maquina.setN_maquina(rs.getInt("n_maquina"));
					maquina.setN_registro(rs.getInt("n_registro"));
					maquina.setMarca(rs.getString("marca"));
					maquina.setPotencia(rs.getFloat("potencia"));
					maquina.setValor_diaria(rs.getFloat("val_diaria"));
					maquina.setTipo_combustivel(rs.getString("tipo_combust"));
					maquina.setHorimetro(rs.getInt("horimetro"));
					maquina.setStatus(rs.getString("status"));

					return maquina;
				}
				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<MaquinaLookUp> list() {
		MaquinaLookUp maquina;
		Database bd = new Database();
		Connection conn = null;

		List<MaquinaLookUp> maquinas = new ArrayList<MaquinaLookUp>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM MAQUINA_LIST");

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					maquina = new MaquinaLookUp();

					maquina.setN_maquina(rs.getInt("n_maquina"));
					maquina.setN_registro(rs.getInt("n_registro"));
					maquina.setMarca(rs.getString("marca"));
					maquina.setPotencia(rs.getFloat("potencia"));
					maquina.setValor_diaria(rs.getFloat("val_diaria"));
					maquina.setTipo_combustivel(rs.getString("tipo_combust"));
					maquina.setData_ult_revisao(rs.getDate("data_ult_revisao"));
					maquina.setHorimetro(rs.getInt("horimetro"));
					maquina.setStatus(rs.getString("status"));

					maquinas.add(maquina);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return maquinas;
	}

	public void update(MaquinaLookUp maquina) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE MAQUINA_UPDATE(?,?,?,?,?,?)");

				NumberFormat df = new DecimalFormat("#0.00");

				st.setInt(1, maquina.getN_maquina());
				st.setInt(2, maquina.getN_registro());
				st.setString(3, maquina.getMarca());
				st.setFloat(4, Float.parseFloat(df.format(maquina.getPotencia())));
				st.setFloat(5, Float.parseFloat(df.format(maquina.getValor_diaria())));
				st.setString(6, maquina.getTipo_combustivel());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String maquina_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("DELETE FROM REVISAO WHERE N_MAQUINA_FK = ?");
				st.setInt(1, Integer.parseInt(maquina_id));
				st.execute();

				st = conn.prepareStatement("DELETE FROM AlUGUEL WHERE N_MAQUINA_FK = ?");
				st.setInt(1, Integer.parseInt(maquina_id));
				st.execute();

				st = conn.prepareStatement("EXECUTE PROCEDURE MAQUINA_DELETE(?)");
//				PreparedStatement st = conn.prepareStatement("DELETE FROM MAQUINA WHERE N_MAQUINA = ?");
				st.setInt(1, Integer.parseInt(maquina_id));
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<AluguelBean> getLastAlugueis(String maquina_id) {
		ArrayList<AluguelBean> alugueis = new ArrayList<AluguelBean>();
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();
			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT FIRST 5 * FROM ALUGUEL WHERE N_MAQUINA_FK = ?");

				st.setString(1, maquina_id);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					AluguelBean aluguel = new AluguelBean();

					aluguel.setData_entregue(rs.getDate("data_entregue"));
					aluguel.setData_final(rs.getDate("data_final"));
					aluguel.setData_ini(rs.getDate("data_ini"));
					aluguel.setN_aluguel(rs.getInt("n_aluguel"));
					aluguel.setHori_saida(rs.getInt("hori_saida"));
					aluguel.setHori_retorno(rs.getInt("hori_retorno"));

					alugueis.add(aluguel);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alugueis;
	}

	public ArrayList<RevisaoBean> getLastRevisoes(String maquina_id) {
		ArrayList<RevisaoBean> revisoes = new ArrayList<RevisaoBean>();
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();
			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT FIRST 5 * FROM REVISAO WHERE N_MAQUINA_FK = ?");

				st.setString(1, maquina_id);

				ResultSet rs = st.executeQuery();

				String date;

				while (rs.next()) {
					RevisaoBean revisao = new RevisaoBean();

//					revisao.setN_revisao(rs.getInt("n_revisao"));
//					revisao.setN_maquina_fk(rs.getInt("n_maquina_fk"));

					date = rs.getString("data");
					revisao.setData(date != null ? RevisaoBean.dateFromString(date) : null);
					revisao.setMotivo(rs.getString("motivo"));
					revisao.setHorimetro(rs.getInt("horimetro"));

					date = rs.getString("data_retorno");
					revisao.setData_retorno(date != null ? RevisaoBean.dateFromString(date) : null);
					revisao.setHori_retorno(rs.getInt("hori_retorno"));

					revisoes.add(revisao);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return revisoes;
	}

	public List<String> marcas() {
		Database db = new Database();
		Connection conn = null;
		List<String> marcas = new ArrayList<String>();

		try {
			conn = db.getConnection();
			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM MARCA_LIST");

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					marcas.add(rs.getString("marca"));
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return marcas;
	}

	public List<String> tipo_combust() {
		Database db = new Database();
		Connection conn = null;
		List<String> tipos_combust = new ArrayList<String>();

		try {
			conn = db.getConnection();
			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM tipo_combust_list");

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					tipos_combust.add(rs.getString("tipo_combust"));
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tipos_combust;
	}

	public void createRevisao(RevisaoBean revisao) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();
			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE REVISAO_CREATE(?, ?, ?, ?)");

				st.setInt(1, revisao.getN_maquina_fk());
				st.setString(2, revisao.getData().toString());
				st.setString(3, revisao.getMotivo());
				st.setInt(4, revisao.getHorimetro());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cancelRevisao(RevisaoBean revisao) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();
			if (conn != null) {
				PreparedStatement st = conn.prepareStatement(
						"UPDATE REVISAO SET DATA_RETORNO=?, HORI_RETORNO=? WHERE N_REVISAO=(SELECT MAX(N_REVISAO) FROM REVISAO)");

				st.setString(1, revisao.getData_retorno().toString());
				st.setInt(2, revisao.getHori_retorno());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
