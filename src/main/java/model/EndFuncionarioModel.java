package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.EndFuncionarioBean;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class EndFuncionarioModel 
{
	public Boolean create(EndFuncionarioBean endfuncionario) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();


		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_FUNCIONARIO_CREATE(?,?,?,?,?,?,?)");
				
				st.setInt(1,endfuncionario.getN_funcionario_fk());
				st.setString(2,endfuncionario.getUf() );
				st.setString(3, endfuncionario.getCidade());
				st.setString(4, endfuncionario.getBairro());
				st.setString(5, endfuncionario.getRua());
				st.setString(6, endfuncionario.getNumero());
				st.setString(7, endfuncionario.getCep());

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
	public List<EndFuncionarioBean> readEnderecos(String funcionario_id) {
		EndFuncionarioBean endereco;
		Database bd = new Database();
		Connection conn = null;

		List<EndFuncionarioBean> enderecos = new ArrayList<EndFuncionarioBean>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM END_FUNCIONARIO WHERE N_FUNCIONARIO_FK = ?");

				st.setString(1, funcionario_id);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					endereco = new EndFuncionarioBean();

					endereco.setN_end(rs.getInt("n_end"));
					endereco.setN_funcionario_fk(rs.getInt("n_funcionario_fk"));
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

	public void deleteAll(String funcionario_id) {
		Database db = new Database();
		Connection conn = null;

		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("DELETE FROM END_FUNCIONARIO WHERE N_FUNCIONARIO = ?");
				st.setInt(1, Integer.parseInt(funcionario_id));
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

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_FUNCIONARIO_DELETE(?)");
				st.setInt(1, endereco_id);
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(EndFuncionarioBean endereco) {
		Database db = new Database();
		Connection conn = null;

		try {
			conn = db.getConnection();

			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE END_FUNCIONARIO_UPDATE(?,?,?,?,?,?,?,?)");

				st.setInt(1, endereco.getN_end());
				st.setInt(2, endereco.getN_funcionario_fk());
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
