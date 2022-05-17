package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	private static final String ID = "webdb";
	private static final String PASSWORD = "webdb";
	
	public static void main(String[] args) {
		Connection connecion = null;
		
		try {
			// 1. JDBC Driver 로딩(JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.10.40:3306/webdb?charset=utf8";
			connecion = DriverManager.getConnection(url, ID, PASSWORD);
			
			System.out.println("connected!");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(connecion != null)
					connecion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
