package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.TelClienteBean;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class TelClienteModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Boolean create(TelClienteBean tel) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_CLIENTE_CREATE(?,?,?)");

				st.setInt(1, tel.getN_cliente_fk());
				st.setInt(2, tel.getDdd());
				st.setInt(3, tel.getNumero_tel());

				st.execute();

				st.close();
				conn.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public List<TelClienteBean> readTels(String cliente_id) {
		TelClienteBean tel;
		Database bd = new Database();
		Connection conn = null;

		List<TelClienteBean> tels = new ArrayList<TelClienteBean>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM TEL_CLIENTE WHERE N_CLIENTE_FK = ?");

				st.setString(1, cliente_id);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					tel = new TelClienteBean();

					tel.setN_telefone(rs.getInt("n_telefone"));
					tel.setN_cliente_fk(rs.getInt("n_cliente_fk"));
					tel.setDdd(rs.getInt("ddd"));
					tel.setNumero_tel(rs.getInt("numero_tel"));
				
					tels.add(tel);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tels;
	}
	
	public void deleteAll(String cliente_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("DELETE FROM TEL_CLIENTE WHERE N_CLIENTE = ?");
				st.setInt(1, Integer.parseInt(cliente_id));
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int tel_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_CLIENTE_DELETE(?)");
				st.setInt(1, tel_id);
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void update(TelClienteBean tel) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_CLIENTE_UPDATE(?,?,?,?)");

				st.setInt(1, tel.getN_telefone());
				st.setInt(2, tel.getN_cliente_fk());
				st.setInt(3, tel.getDdd());
				st.setInt(4, tel.getNumero_tel());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
