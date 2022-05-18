package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest02 {
	private static final String ID = "webdb";
	private static final String PASSWORD = "webdb";
	
	public static void main(String[] args) {
	delete(7L);
	//	delete();
	}
	
	public static void delete() {
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver 로딩(JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.10.40:3306/webdb?charset=utf8";
			connecion = DriverManager.getConnection(url, ID, PASSWORD);
			
			// 3. SQL 준비
			String sql = "delete from department";
			pstmt = connecion.prepareStatement(sql);
			
			// 4. Parameter Mapping
			
			// 5. SQL 실행
			pstmt.executeUpdate();

				
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(connecion != null)
					connecion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static boolean delete(Long no) {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver 로딩(JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.10.40:3306/webdb?charset=utf8";
			connecion = DriverManager.getConnection(url, ID, PASSWORD);
			
			// 3. SQL 준비
			String sql = "delete from department where no = ?";
			pstmt = connecion.prepareStatement(sql);
			
			// 4. Parameter Mapping
			pstmt.setLong(1, no);
			
			// 5. SQL 실행
			int count =pstmt.executeUpdate();
			result = count == 1;
				
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(connecion != null)
					connecion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
