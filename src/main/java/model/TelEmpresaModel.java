package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.TelEmpresaBean;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class TelEmpresaModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Boolean create(TelEmpresaBean tel) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_EMPRESA_CREATE(?,?,?)");

				st.setInt(1, tel.getN_empresa_fk());
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
	
	public List<TelEmpresaBean> readTels(String empresa_id) {
		TelEmpresaBean tel;
		Database bd = new Database();
		Connection conn = null;

		List<TelEmpresaBean> tels = new ArrayList<TelEmpresaBean>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM TEL_EMPRESA WHERE N_EMPRESA_FK = ?");

				st.setString(1, empresa_id);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					tel = new TelEmpresaBean();

					tel.setN_telefone(rs.getInt("n_telefone"));
					tel.setN_empresa_fk(rs.getInt("n_empresa_fk"));
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
	
	public void deleteAll(String empresa_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("DELETE FROM TEL_EMPRESA WHERE N_EMPRESA = ?");
				st.setInt(1, Integer.parseInt(empresa_id));
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

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_EMPRESA_DELETE(?)");
				st.setInt(1, tel_id);
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void update(TelEmpresaBean tel) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_EMPRESA_UPDATE(?,?,?,?)");

				st.setInt(1, tel.getN_telefone());
				st.setInt(2, tel.getN_empresa_fk());
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
