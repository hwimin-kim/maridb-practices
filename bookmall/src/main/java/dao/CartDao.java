package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vo.CartVo;


public class CartDao {
	private static final String ID = "bookmall";
	private static final String PASSWORD = "bookmall";
	private static final String URL = "jdbc:mysql://192.168.10.40:3306/bookmall?charset=utf8";

	public void insert(Long memberNo, Long bookNo, Long count) {
		CartVo vo = new CartVo();	
		vo.setMemberNo(memberNo);
		vo.setBookNo(bookNo);
		vo.setCount(count);
		
		insert(vo);
	}
	
	public boolean insert(CartVo vo) {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "insert into cart values(?, ?, ?)";
			pstmt = connecion.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getCount());
	
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
			
			String sql = "delete from cart";
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
			
			String sql = "alter table cart auto_increment = 1";
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
	
	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();
		Connection connecion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			connecion = getConnection();
				
			String sql = "select a.name, b.title, c.count from member a, book b, cart c where a.no = c.member_no and b.no = c.book_no";
			pstmt = connecion.prepareStatement(sql);
				
			rs =pstmt.executeQuery();		
				
			while(rs.next()) {
				String name = rs.getString(1);
				String title = rs.getString(2);
				Long count = rs.getLong(3);
					
				CartVo vo = new CartVo();
				vo.setMemberName(name);
				vo.setBookTitle(title);
				vo.setCount(count);				
				
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
