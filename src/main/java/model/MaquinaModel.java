package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;
import bean.MaquinaBean;
import lookUp.MaquinaLookUp;
import resources.Database;

public class MaquinaModel {

	public void create(MaquinaBean maquina) {
		
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();
//
//		MaquinaBean maquina = new MaquinaBean();
//		maquina.setMarca("asdadasdsad");
//		maquina.setTipo_combustivel("klajlsdjka");
//		maquina.setConsumo(3.43f);
//		maquina.setN_registro(987);
//		maquina.setValor_diaria(98.2f);

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public ArrayList<MaquinaLookUp> list() {
		MaquinaLookUp maquina;
		Database bd = new Database();
		Connection conn = null;

		ArrayList<MaquinaLookUp> maquinas = new ArrayList<MaquinaLookUp>();

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

//	public static void main(String[] args) {
//		MaquinaModel maquina = new MaquinaModel();
//		ArrayList<MaquinaLookUp> maquinas = maquina.list();
//		if(maquinas.size() > 0)
//			System.out.println("tem algo");
//	}

}
