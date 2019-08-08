package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.EndEmpresaBean;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class EndEmpresaModel 
{
	public void create(EndEmpresaBean endempresa) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
