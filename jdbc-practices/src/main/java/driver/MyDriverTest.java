package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {
	
	private static final String ID = "webdb";
	private static final String PASSWORD = "webdb";

	public static void main(String[] args) {
		Connection connecion = null;
			
		try {
			// 1. JDBC Driver 로딩(JDBC Class 로딩: class loader)
			Class.forName("driver.MyDriver");

			// 2. 연결하기
			String url = "jdbc:mydb://127.0.0.1:4404/webdb";			
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
