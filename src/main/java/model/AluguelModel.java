package model;

import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.AluguelBean;
import lookUp.AluguelLookUpList;
import resources.Database;
import resources.HUtil;

@ManagedBean
@ApplicationScoped
public class AluguelModel implements Serializable {
	public int create(AluguelBean aluguel) {
		Database db = new Database();
		Connection conn = null;
		
		int id = 0;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE ALUGUEL_CREATE(?,?,?,?,?,?,?,?)");

				st.setInt(1, aluguel.getN_cliente_fk());
				st.setInt(2, aluguel.getN_funcionario_fk());
				st.setInt(3, aluguel.getN_maquina_fk());
				st.setDate(4, HUtil.dateToSql(aluguel.getData_ini()));
				st.setDate(5, HUtil.dateToSql(aluguel.getData_final()));
				st.setInt(6, aluguel.getHori_saida());
				st.setFloat(7, aluguel.getVal_contratado());
				st.setFloat(8, aluguel.getTempo_hd());
				st.execute();
				
				st = conn.prepareStatement("SELECT * FROM ALUGUEL_ID");

				ResultSet rs = st.executeQuery();

				if(rs.next()) {
					id = rs.getInt("id");
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public void update(AluguelBean aluguel) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE ALUGUEL_UPDATE(?,?,?,?,?,?,?,?,?)");
				st.setInt(1, aluguel.getN_aluguel());
				st.setInt(2, aluguel.getN_cliente_fk());
				st.setInt(3, aluguel.getN_funcionario_fk());
				st.setInt(4, aluguel.getN_maquina_fk());
				st.setDate(5, HUtil.dateToSql(aluguel.getData_ini()));
				st.setDate(6, HUtil.dateToSql(aluguel.getData_final()));
				st.setInt(7, aluguel.getHori_saida());
				st.setFloat(8, aluguel.getVal_contratado());
				st.setFloat(9, aluguel.getTempo_hd());
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(AluguelBean aluguel) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE ALUGUEL_DELETE(?)");
				st.setInt(1, aluguel.getN_aluguel());
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setEmpresa(AluguelBean aluguel) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE ALUGUEL_SET_EMPRESA(?,?)");
				st.setInt(1, aluguel.getN_aluguel());
				st.setInt(2, aluguel.getN_empresa_fk());
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void payment(AluguelBean aluguel, float dinheiro) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE ALUGUEL_PAYMENT(?,?)");
				st.setInt(1, aluguel.getN_aluguel());
				st.setFloat(2, dinheiro);
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retorno(AluguelBean aluguel) {
		Database db = new Database();
		Connection conn = null;

		conn = db.getConnection();

		try {
			if (conn != null) {

				PreparedStatement st = conn.prepareStatement("EXECUTE PROCEDURE ALUGUEL_RETURN(?,?,?,?)");
				st.setInt(1, aluguel.getN_aluguel());
				st.setDate(2, HUtil.dateToSql(aluguel.getData_entregue()));
				st.setInt(3, aluguel.getHori_retorno());
				st.setFloat(4, aluguel.getVal_contratado());
				st.execute();

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<AluguelLookUpList> list() {
		AluguelLookUpList aluguel;
		Database bd = new Database();
		Connection conn = null;

		List<AluguelLookUpList> alugueis = new ArrayList<AluguelLookUpList>();

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM ALUGUEL_LIST");

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					aluguel = new AluguelLookUpList();
					aluguel.setN_aluguel(rs.getInt("n_aluguel"));
					aluguel.setData_ini(HUtil.dateToUtil(rs.getDate("data_ini")));
					aluguel.setData_entregue(HUtil.dateToUtil(rs.getDate("data_entregue")));
					aluguel.setN_registro(rs.getInt("n_registro"));
					aluguel.setPotencia(rs.getFloat("potencia"));
					aluguel.setVal_contratado(rs.getFloat("val_contratado"));
					aluguel.setValor_pago(rs.getFloat("valor_pago"));
					alugueis.add(aluguel);
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alugueis;
	}

	public AluguelLookUpList read(int n_aluguel) {
		AluguelLookUpList aluguel;
		Database bd = new Database();
		Connection conn = null;

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM ALUGUEL_LIST WHERE N_ALUGUEL = ?");
				
				st.setInt(1, n_aluguel);

				ResultSet rs = st.executeQuery();

				if (rs.next()) {
					aluguel = new AluguelLookUpList();
					aluguel.setN_aluguel(rs.getInt("n_aluguel"));
					aluguel.setData_ini(HUtil.dateToUtil(rs.getDate("data_ini")));
					aluguel.setData_entregue(HUtil.dateToUtil(rs.getDate("data_entregue")));
					aluguel.setN_registro(rs.getInt("n_registro"));
					aluguel.setPotencia(rs.getFloat("potencia"));
					aluguel.setVal_contratado(rs.getFloat("val_contratado"));
					aluguel.setValor_pago(rs.getFloat("valor_pago"));
					return aluguel;
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public AluguelBean readBean(int n_aluguel) {
		AluguelBean aluguel;
		Database bd = new Database();
		Connection conn = null;

		try {
			conn = bd.getConnection();

			if (conn != null) {
				PreparedStatement st = conn.prepareStatement("SELECT * FROM ALUGUEL WHERE N_ALUGUEL = ?");
				
				st.setInt(1, n_aluguel);

				ResultSet rs = st.executeQuery();

				if (rs.next()) {
					aluguel = new AluguelBean();
					aluguel.setN_aluguel(rs.getInt("n_aluguel"));
					aluguel.setData_ini(HUtil.dateToUtil(rs.getDate("data_ini")));
					aluguel.setData_entregue(HUtil.dateToUtil(rs.getDate("data_entregue")));
					aluguel.setData_final(HUtil.dateToUtil(rs.getDate("data_final")));
					aluguel.setVal_contratado(rs.getFloat("val_contratado"));
					aluguel.setValor_pago(rs.getFloat("valor_pago"));
					aluguel.setHori_retorno(rs.getInt("hori_retorno"));
					aluguel.setHori_saida(rs.getInt("hori_saida"));
					aluguel.setN_cliente_fk(rs.getInt("n_cliente_fk"));
					aluguel.setN_empresa_fk(rs.getInt("n_empresa_fk"));
					aluguel.setN_funcionario_fk(rs.getInt("n_funcionario_fk"));
					aluguel.setN_maquina_fk(rs.getInt("n_maquina_fk"));
					aluguel.setTempo_hd(rs.getFloat("tempo_hd"));
					return aluguel;
				}

				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main(String[] args) {
//		AluguelBean l = new AluguelBean();
//		l.setN_cliente_fk(0);
//		l.setN_funcionario_fk(0);
//		l.setN_maquina_fk(0);
//		l.setData_ini(new Date(1564617600000l));
//		l.setData_final(new Date(1565481600000l));
//		l.setHori_saida(123456);
//		l.setVal_contratado(723.8f);
//		l.setTempo_hd(6);
//		(new AluguelModel()).create(l);
		
		List<AluguelLookUpList> alugueis = (new AluguelModel()).list();
		
		for(int i=0;i<alugueis.size();i++) {
			System.out.println(alugueis.get(i));
		}
	}
}
