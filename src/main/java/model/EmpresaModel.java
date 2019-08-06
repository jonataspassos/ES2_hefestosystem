package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.EmpresaBean;
import resources.Database;

public class EmpresaModel 
{
	public void create(EmpresaBean empresa) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();


		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE CLIENTE_CREATE(?,?)");
				
				st.setString(1, "" + empresa.getCnpj());
				st.setString(2, empresa.getRaz_social());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
