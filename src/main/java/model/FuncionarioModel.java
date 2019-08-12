package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.ClienteBean;
import bean.FuncionarioBean;
import lookUp.ClienteLookUpList;
import lookUp.FuncionarioLookUp;
import resources.Database;

@ManagedBean
@ApplicationScoped
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
	
	public List<FuncionarioLookUp> list(){
		FuncionarioLookUp funcionario;
		Database bd = new Database();
		Connection conn = null;

		List<FuncionarioLookUp> funcionarios = new ArrayList<FuncionarioLookUp>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM FUNCIONARIO_LIST");

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					funcionario = new FuncionarioLookUp();
					funcionario.setN_funcionario(rs.getInt("n_funcionario"));
					funcionario.setCpf(rs.getNString("cpf"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setTelefone(rs.getString("telefone"));
					funcionarios.add(funcionario);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return funcionarios;
	} 
	
	public FuncionarioBean readBean(FuncionarioBean func) {
		FuncionarioBean funcionario;
		Database db = new Database();
		Connection conn = null;
		
		try {

			conn = db.getConnection();
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("SELECT * FROM FUNCIONARIO WHERE CPF = ?");
				
				st.setString(1, func.getCpf());

				ResultSet rs = st.executeQuery();
				
				if(rs.next()) {
					funcionario = new FuncionarioBean();
					
					funcionario.setN_funcionario(rs.getInt("n_funcionario"));
					funcionario.setCpf(rs.getString("cpf"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setSenha(rs.getString("senha"));
					funcionario.setStatus(rs.getString("status"));
					
					if(func.getSenha().equals(funcionario.getSenha()))
						return funcionario;
				}
				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
	public void novaSenha(FuncionarioBean funcionario, String senha1, String senha2){
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();


		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE FUNCIONARIO_SET_PASSWORD(?,?,?,?)");
				
				st.setString(1, "" + funcionario.getN_funcionario());
				st.setString(2, funcionario.getSenha());
				st.setString(3, senha1);
				st.setString(4, senha2);

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

	public FuncionarioLookUp read(int n_funcionario) {
		FuncionarioLookUp funcionario;
		Database bd = new Database();
		Connection conn = null;

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM FUNCIONARIO_LIST WHERE N_FUNCIONARIO = ?");
				
				st.setInt(1, n_funcionario);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					funcionario = new FuncionarioLookUp();
					funcionario.setN_funcionario(rs.getInt("n_funcionario"));
					funcionario.setCpf(rs.getNString("cpf"));
					funcionario.setNome(rs.getString("nome"));
					funcionario.setTelefone(rs.getString("telefone"));
					return funcionario;
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
