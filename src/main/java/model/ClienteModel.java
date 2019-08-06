package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.ClienteBean;
import resources.Database;

public class ClienteModel 
{
	public void create(ClienteBean cliente) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();


		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE CLIENTE_CREATE(?,?)");
				
				st.setString(1, "" + cliente.getCpf());
				st.setString(2, cliente.getNome());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
