package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private Connection con = null;
	private String hostName = null;
	private String userName = null;
	private String password = null;
	private String url = null;
	private String jdbcDriver = null;
	private String dataBaseName = null;
	private String dataBasePrefix = null;
	
	public Database() {

		jdbcDriver = "org.firebirdsql.jdbc.FBDriver";

		dataBasePrefix = "jdbc:firebirdsql";

		dataBaseName = "hefestosystem";
		userName = "SYSDBA";
		hostName = "localhost";
		password = "masterkey";
		url = dataBasePrefix + ":" + hostName + ":" + dataBaseName;

	}
	
	public Connection getConnection() {
		
		Runtime.getRuntime().gc();
		try {
			if (con == null) {
				Class.forName(jdbcDriver);
				con = DriverManager.getConnection(url
						+ "?defaultResultSetHoldable=True", userName, password);
			} else if (con.isClosed()) {
				con = null;
				return getConnection();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection connect() {
		Database bc = new Database();
		Connection conn = null;
		
		conn = bc.getConnection();
		return conn;
	}
	public static void main(String [] args) {
		Connection conn = connect();
		System.out.println(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
