package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.ClienteBean;
import bean.FuncionarioBean;
import resources.Database;

public class FuncionarioModel 
{
	public void create(FuncionarioBean funcionario) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();


		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE FUNCIONARIO_CREATE(?,?)");
				
				st.setString(1, "" + funcionario.getCpf());
				st.setString(2, funcionario.getNome());

				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String []args) {
		FuncionarioModel func = new FuncionarioModel();
		FuncionarioBean funcBean = new FuncionarioBean();
		
		funcBean.setCpf("93221351298");
		funcBean.setNome("Cigano");
		
		func.create(funcBean);
		
	}

}
