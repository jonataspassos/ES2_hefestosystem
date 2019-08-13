package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.EndEmpresaBean;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class EndEmpresaModel implements Serializable {
	public Boolean create(EndEmpresaBean endempresa) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_EMPRESA_CREATE(?,?,?,?,?,?,?)");

				st.setInt(1, endempresa.getN_empresa_fk());
				st.setString(2, endempresa.getUf());
				st.setString(3, endempresa.getCidade());
				st.setString(4, endempresa.getBairro());
				st.setString(5, endempresa.getRua());
				st.setString(6, endempresa.getNumero());
				st.setString(7, endempresa.getCep());

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
	public List<EndEmpresaBean> readEnderecos(String empresa_id) {
		EndEmpresaBean endereco;
		Database bd = new Database();
		Connection conn = null;

		List<EndEmpresaBean> enderecos = new ArrayList<EndEmpresaBean>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM END_EMPRESA WHERE N_EMPRESA_FK = ?");

				st.setString(1, empresa_id);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					endereco = new EndEmpresaBean();

					endereco.setN_end(rs.getInt("n_end"));
					endereco.setN_empresa_fk(rs.getInt("n_empresa_fk"));
					endereco.setUf(rs.getString("uf"));
					endereco.setCidade(rs.getString("cidade"));
					endereco.setBairro(rs.getString("bairro"));
					endereco.setRua(rs.getString("rua"));
					endereco.setNumero(rs.getString("numero"));
					endereco.setCep(rs.getString("cep"));
					enderecos.add(endereco);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return enderecos;
	}

	public void deleteAll(String empresa_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("DELETE FROM END_EMPRESA WHERE N_EMPRESA = ?");
				st.setInt(1, Integer.parseInt(empresa_id));
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int endereco_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_EMPRESA_DELETE(?)");
				st.setInt(1, endereco_id);
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(EndEmpresaBean endereco) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_EMPRESA_UPDATE(?,?,?,?,?,?,?,?)");

				st.setInt(1, endereco.getN_end());
				st.setInt(2, endereco.getN_empresa_fk());
				st.setString(3, endereco.getUf());
				st.setString(4, endereco.getCidade());
				st.setString(5, endereco.getBairro());
				st.setString(6, endereco.getRua());
				st.setString(7, endereco.getNumero());
				st.setString(8, endereco.getCep());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
