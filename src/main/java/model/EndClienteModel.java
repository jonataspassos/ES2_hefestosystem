package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.EndClienteBean;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class EndClienteModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public Boolean create(EndClienteBean endcliente) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_CLIENTE_CREATE(?,?,?,?,?,?,?)");

				st.setInt(1, endcliente.getN_cliente_fk());
				st.setString(2, endcliente.getUf());
				st.setString(3, endcliente.getCidade());
				st.setString(4, endcliente.getBairro());
				st.setString(5, endcliente.getRua());
				st.setString(6, endcliente.getNumero());
				st.setString(7, endcliente.getCep());

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

	public List<EndClienteBean> readEnderecos(String cliente_id) {
		EndClienteBean endereco;
		Database bd = new Database();
		Connection conn = null;

		List<EndClienteBean> enderecos = new ArrayList<EndClienteBean>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM END_CLIENTE WHERE N_CLIENTE_FK = ?");

				st.setString(1, cliente_id);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					endereco = new EndClienteBean();

					endereco.setN_end(rs.getInt("n_end"));
					endereco.setN_cliente_fk(rs.getInt("n_cliente_fk"));
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

	public void deleteAll(String cliente_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("DELETE FROM END_CLIENTE WHERE N_CLIENTE = ?");
				st.setInt(1, Integer.parseInt(cliente_id));
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

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_CLIENTE_DELETE(?)");
				st.setInt(1, endereco_id);
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(EndClienteBean endereco) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_CLIENTE_UPDATE(?,?,?,?,?,?,?,?)");

				st.setInt(1, endereco.getN_end());
				st.setInt(2, endereco.getN_cliente_fk());
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
