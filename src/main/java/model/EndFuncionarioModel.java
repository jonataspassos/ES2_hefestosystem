package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.EndFuncionarioBean;
import resources.Database;

public class EndFuncionarioModel 
{
	public void create(EndFuncionarioBean endfuncionario) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
