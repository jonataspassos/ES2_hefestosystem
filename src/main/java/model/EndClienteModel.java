package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.EndClienteBean;
import resources.Database;

public class EndClienteModel 
{
	public void create(EndClienteBean endcliente) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
