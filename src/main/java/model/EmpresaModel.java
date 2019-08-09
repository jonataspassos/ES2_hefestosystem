package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.EmpresaBean;
import lookUp.ClienteLookUpList;
import lookUp.EmpresaLookUpList;
import resources.Database;

@ManagedBean
@ApplicationScoped
public class EmpresaModel implements Serializable {
	public void create(EmpresaBean empresa) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE EMPRESA_CREATE(?,?)");

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
	
	public List<EmpresaLookUpList> list(){
		EmpresaLookUpList empresa;
		Database bd = new Database();
		Connection conn = null;

		List<EmpresaLookUpList> empresas = new ArrayList<EmpresaLookUpList>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM EMPRESA_LIST");

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					empresa = new EmpresaLookUpList();
					empresa.setN_empresa(rs.getInt("n_empresa"));
					empresa.setCnpj(rs.getNString("cnpj"));
					empresa.setRaz_social(rs.getString("raz_social"));
					empresa.setTelefone(rs.getString("telefone"));
					empresa.setN_alugueis(rs.getInt("n_alugueis"));
					empresas.add(empresa);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empresas;
	}
	
	public EmpresaLookUpList read(String cnpj) {
		EmpresaLookUpList empresa;
		Database bd = new Database();
		Connection conn = null;

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM EMPRESA_LIST WHERE CNPJ = ?");
				
				st.setString(1, cnpj);

				ResultSet rs = st.executeQuery();

				if (rs.next()) {
					empresa = new EmpresaLookUpList();
					empresa.setN_empresa(rs.getInt("n_empresa"));
					empresa.setCnpj(rs.getNString("cnpj"));
					empresa.setRaz_social(rs.getString("raz_social"));
					empresa.setTelefone(rs.getString("telefone"));
					empresa.setN_alugueis(rs.getInt("n_alugueis"));
					return empresa;
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
