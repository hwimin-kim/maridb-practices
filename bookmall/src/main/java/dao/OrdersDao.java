package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vo.OrdersVo;

public class OrdersDao {
	private static final String ID = "bookmall";
	private static final String PASSWORD = "bookmall";
	private static final String URL = "jdbc:mysql://192.168.10.40:3306/bookmall?charset=utf8";
	
	public void insert(String ordersNumber, Long pay, String receive, Long memberNo) {
		OrdersVo vo = new OrdersVo();	
		vo.setOrdersNumber(ordersNumber);
		vo.setPay(pay);
		vo.setReceive(receive);
		vo.setMemberNo(memberNo);
		
		insert(vo);
	}
	
	public boolean insert(OrdersVo vo) {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "insert into orders values(null, ?, ?, ?, ?)";
			pstmt = connecion.prepareStatement(sql);
			
			pstmt.setString(1, vo.getOrdersNumber());
			pstmt.setLong(2, vo.getPay());
			pstmt.setString(3, vo.getReceive());
			pstmt.setLong(4, vo.getMemberNo());
	
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
	
	public void insertByBook(Long bookNo, Long orderNo, Long count) {
		OrdersVo vo = new OrdersVo();	
		vo.setBookNo(bookNo);
		vo.setNo(orderNo);
		vo.setCount(count);
		
		insertByBook(vo);
	}
	
	public boolean insertByBook(OrdersVo vo) {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "insert into orders_book values(?, ?, ?)";
			pstmt = connecion.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getBookNo());
			pstmt.setLong(2, vo.getNo());
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
	
	public List<OrdersVo> findByBook() {
		List<OrdersVo> result = new ArrayList<>();
		Connection connecion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			connecion = getConnection();
				
			String sql = "select a.no, a.title, b.count, a.price * b.count as total_pay from book a, orders_book b where a.no = b.book_no";
			pstmt = connecion.prepareStatement(sql);
				
			rs =pstmt.executeQuery();		
				
			while(rs.next()) {
				Long bookNo = rs.getLong(1);
				String bookTitle = rs.getString(2);
				Long count = rs.getLong(3);
				Long totalPay = rs.getLong(4);		
					
				OrdersVo vo = new OrdersVo();
				vo.setBookNo(bookNo);
				vo.setBookTitle(bookTitle);
				vo.setCount(count);
				vo.setTotalPay(totalPay);
						
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
	
	public boolean DeleteAll() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "delete from orders";
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
	
	public boolean DeleteAllByBook() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "delete from orders_book";
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
			
			String sql = "alter table orders auto_increment = 1";
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
	
	public boolean autoIncrementResetByBook() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "alter table orders_book auto_increment = 1";
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
	
	
	public List<OrdersVo> findAll() {
		List<OrdersVo> result = new ArrayList<>();
		Connection connecion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			connecion = getConnection();
				
			String sql = "select b.orders_number, a.name, a.email, b.pay, b.receive from member a, orders b where a.no = b.member_no";
			pstmt = connecion.prepareStatement(sql);
				
			rs =pstmt.executeQuery();		
				
			while(rs.next()) {
				String orderNumber = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				Long pay = rs.getLong(4);
				String receive = rs.getString(5);
					
				OrdersVo vo = new OrdersVo();
				vo.setOrdersNumber(orderNumber);
				vo.setMemberName(name);
				vo.setMemberEmail(email);
				vo.setPay(pay);
				vo.setReceive(receive);
				
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
