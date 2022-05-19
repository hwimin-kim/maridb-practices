package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vo.BookVo;


public class BookDao {
	private static final String ID = "bookmall";
	private static final String PASSWORD = "bookmall";
	private static final String URL = "jdbc:mysql://192.168.10.40:3306/bookmall?charset=utf8";

	public void insert(String title, Long price, Long categoryNo) {
		BookVo vo = new BookVo();		
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		
		insert(vo);
	}
	
	public boolean insert(BookVo vo) {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = connecion.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
	
			int count =pstmt.executeUpdate();
			result = count == 1;
				
		}  catch (SQLException e) {
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
	
	public boolean DeleteAll() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "delete from book";
			pstmt = connecion.prepareStatement(sql);
					
			int count =pstmt.executeUpdate();
			result = count == 1;
				
		}  catch (SQLException e) {
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
	
	public boolean autoIncrementReset() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "alter table book auto_increment = 1";
			pstmt = connecion.prepareStatement(sql);
					
			int count =pstmt.executeUpdate();
			result = count == 1;
				
		}  catch (SQLException e) {
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
	
	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		Connection connecion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			connecion = getConnection();
				
			String sql = "select a.no, a.title, a.price, b.name from book a, category b where a.category_no = b.no order by a.no";
			pstmt = connecion.prepareStatement(sql);
				
			rs =pstmt.executeQuery();		
				
			while(rs.next()) {
				Long no =  rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				String name = rs.getString(4);
					
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategoryName(name);
				
				result.add(vo);
			}
				
			}  catch (SQLException e) {
				System.out.println("드라이버 로딩 실패:" + e);
			} finally {
				try {
					if(rs != null)
						rs.close();
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
	
	private Connection getConnection() throws SQLException {
		Connection connecion = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connecion = DriverManager.getConnection(URL, ID, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}	
		return connecion;
	}
}
