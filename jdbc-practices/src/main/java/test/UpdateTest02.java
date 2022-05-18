package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest02 {
	private static final String ID = "webdb";
	private static final String PASSWORD = "webdb";
	
	public static void main(String[] args) {
		// (4L,"전략기획팀");
		DepartmentVo vo = new DepartmentVo();
		vo.setNo(6L);
		vo.setName("기반팀");
		
		update(vo);
	}
	
	public static boolean update(DepartmentVo vo) {
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
			String sql = "update department set name = ? where no = ?";
			pstmt = connecion.prepareStatement(sql);
			
			// 4. Paramether Mapping
			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());
			
			// 4. SQL 실행
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
