package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.ClienteBean;
import lookUp.AluguelLookUpList;
import lookUp.ClienteLookUpList;
import resources.Database;
import resources.HUtil;

@ManagedBean
@ApplicationScoped
public class ClienteModel implements Serializable {
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

	public static void main(String[] args) {
		ClienteModel cliente = new ClienteModel();
		ClienteBean clienteBean = new ClienteBean();

		clienteBean.setCpf("93221351298");
		clienteBean.setNome("Cigano");

		cliente.create(clienteBean);

	}

	public List<ClienteLookUpList> list() {
		ClienteLookUpList cliente;
		Database bd = new Database();
		Connection conn = null;

		List<ClienteLookUpList> clientes = new ArrayList<ClienteLookUpList>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM CLIENTE_LIST");

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					cliente = new ClienteLookUpList();
					cliente.setN_cliente(rs.getInt("n_cliente"));
					cliente.setCpf(rs.getNString("cpf"));
					cliente.setNome(rs.getString("nome"));
					cliente.setTelefone(rs.getString("telefone"));
					cliente.setN_alugueis(rs.getInt("n_alugueis"));
					clientes.add(cliente);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientes;
	}
}
