package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.TelFuncionarioBean;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class TelFuncionarioModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Boolean create(TelFuncionarioBean tel) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_FUNCIONARIO_CREATE(?,?,?)");

				st.setInt(1, tel.getN_funcionario_fk());
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
	
	public List<TelFuncionarioBean> readTels(String funcionario_id) {
		TelFuncionarioBean tel;
		Database bd = new Database();
		Connection conn = null;

		List<TelFuncionarioBean> tels = new ArrayList<TelFuncionarioBean>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM TEL_FUNCIONARIO WHERE N_FUNCIONARIO_FK = ?");

				st.setString(1, funcionario_id);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					tel = new TelFuncionarioBean();

					tel.setN_telefone(rs.getInt("n_telefone"));
					tel.setN_funcionario_fk(rs.getInt("n_funcionario_fk"));
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
	
	public void deleteAll(String funcionario_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("DELETE FROM TEL_FUNCIONARIO WHERE N_FUNCIONARIO = ?");
				st.setInt(1, Integer.parseInt(funcionario_id));
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

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_FUNCIONARIO_DELETE(?)");
				st.setInt(1, tel_id);
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void update(TelFuncionarioBean tel) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE TEL_FUNCIONARIO_UPDATE(?,?,?,?)");

				st.setInt(1, tel.getN_telefone());
				st.setInt(2, tel.getN_funcionario_fk());
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
