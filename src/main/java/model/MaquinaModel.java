package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import bean.MaquinaBean;
//import lookUp.MaquinaLookUp;
import resources.Database;

public class MaquinaModel {

	public static void create() {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		MaquinaBean maquina = new MaquinaBean();
		maquina.setMarca("asdadasdsad");
		maquina.setTipo_combustivel("klajlsdjka");
		maquina.setConsumo(3.43f);
		maquina.setN_registro(987);
		maquina.setValor_diaria(98.2f);

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE MAQUINA_CREATE(?,?,?,?,?)");
//				PreparedStatement st = conn
//						.prepareStatement("INSERT INTO MAQUINA (N_REGISTRO, MARCA, POTENCIA, VAL_DIARIA, "
//								+ "TIPO_COMBUST) VALUES (?, ?, ?, ?, ?)");
				st.setString(1, "" + maquina.getN_registro());
				st.setString(2, maquina.getMarca());
				st.setString(3, "" + maquina.getPotencia());
				st.setString(4, "" + maquina.getValor_diaria());
				st.setString(5, maquina.getTipo_combustivel());
				
				ajsdladajdasdklçakç

//				st.setInt(1, Integer.parseInt(maquina.getUs_vendedor()));
//				st.setInt(2, Integer.parseInt(maquina.getUs_comprador()));
//				st.setString(3, maquina.getData());
//				st.setFloat(4, Float.parseFloat(maquina.getValor()));

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		create();
	}

}
