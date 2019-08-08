package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public Boolean create(MaquinaBean maquina) {

		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE MAQUINA_CREATE(?,?,?,?,?)");

				st.setString(1, "" + maquina.getN_registro());
				st.setString(2, maquina.getMarca());
				st.setString(3, "" + maquina.getPotencia());
				st.setString(4, "" + maquina.getValor_diaria());
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

	public MaquinaBean readBean(Integer maquina_id) {
		MaquinaBean maquina;
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("SELECT * FROM MAQUINA WHERE N_MAQUINA = ?");

				st.setInt(1, maquina_id);

				ResultSet rs = st.executeQuery();

				if (rs.next()) {
					maquina = new MaquinaBean();

					maquina.setN_maquina(rs.getInt("n_maquina"));
					maquina.setN_registro(rs.getInt("n_registro"));
					maquina.setMarca(rs.getString("marca"));
					maquina.setPotencia(rs.getFloat("potencia"));
					maquina.setValor_diaria(rs.getFloat("val_diaria"));
					maquina.setTipo_combustivel(rs.getString("tipo_combust"));

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

	public void update(MaquinaBean maquina) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE MAQUINA_UPDATE(?,?,?,?,?)");

				st.setString(1, "" + maquina.getN_registro());
				st.setString(2, maquina.getMarca());
				st.setString(3, "" + maquina.getPotencia());
				st.setString(4, "" + maquina.getValor_diaria());
				st.setString(5, maquina.getTipo_combustivel());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(MaquinaBean maquina) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE MAQUINA_DELETE(?)");

				st.setInt(1, maquina.getN_maquina());

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
			if(conn != null) {
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
		}catch(Exception e) {
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
			if(conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT FIRST 5 * FROM REVISAO WHERE N_MAQUINA_FK = ?");
				
				st.setString(1, maquina_id);
				
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					RevisaoBean revisao = new RevisaoBean();
					

//					aluguel.setData_entregue(rs.getDate("data_entregue"));
//					aluguel.setData_final(rs.getDate("data_final"));
//					aluguel.setData_ini(rs.getDate("data_ini"));
//					aluguel.setN_aluguel(rs.getInt("n_aluguel"));
//					aluguel.setHori_saida(rs.getInt("hori_saida"));
//					aluguel.setHori_retorno(rs.getInt("hori_retorno"));

					revisoes.add(revisao);
				}
				
				st.close();
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return revisoes;
	}
	
	public List<String> marcas(){
		Database db = new Database();
		Connection conn = null;
		List<String>marcas = new ArrayList<String>();
		
		try {
			conn = db.getConnection();
			if(conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM MARCA_LIST");
				
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					marcas.add(rs.getString("marca"));
				}
				
				st.close();
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return marcas;
	}
	
	public List<String> tipo_combust(){
		Database db = new Database();
		Connection conn = null;
		List<String>tipos_combust = new ArrayList<String>();
		
		try {
			conn = db.getConnection();
			if(conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM tipo_combust_list");
				
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					tipos_combust.add(rs.getString("tipo_combust"));
				}
				
				st.close();
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return tipos_combust;
	}

}
