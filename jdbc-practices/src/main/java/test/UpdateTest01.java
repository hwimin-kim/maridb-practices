package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest01 {
	private static final String ID = "webdb";
	private static final String PASSWORD = "webdb";
	
	public static void main(String[] args) {
		// (4L,"전략기획팀");
		DepartmentVo vo = new DepartmentVo();
		vo.setNo(4L);
		vo.setName("기획");
		
		update(vo);
	}
	
	public static boolean update(DepartmentVo vo) {
		boolean result = false;
		Connection connecion = null;
		Statement stmt = null;
		
		try {
			// 1. JDBC Driver 로딩(JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.10.40:3306/webdb?charset=utf8";
			connecion = DriverManager.getConnection(url, ID, PASSWORD);
			
			// 3. Statement 생성
			stmt = connecion.createStatement();
			
			// 4. SQL 실행
			String sql = "update department set name = '" + vo.getName() + "' where no =" + vo.getNo();
			int count =stmt.executeUpdate(sql);
			result = count == 1;
				
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(stmt != null)
					stmt.close();
				if(connecion != null)
					connecion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
