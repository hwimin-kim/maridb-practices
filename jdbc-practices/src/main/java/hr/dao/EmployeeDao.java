package hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hr.vo.EmployeeVo;

public class EmployeeDao {
	private final String ID = "hr";
	private final String PASSWORD = "hr";
	
	public List<EmployeeVo> findByFirstNameOrLastName(String name) {
		
		List<EmployeeVo> result = new ArrayList<>();
		Connection connecion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver 로딩(JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.10.40:3306/employees?charset=utf8";
			connecion = DriverManager.getConnection(url, ID, PASSWORD);
			
			// 3. SQL 준비
			// limit 100, 이클립스 버퍼 크기
			String sql = "select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d') from employees where first_name like ? or last_name like ? order by hire_date desc limit 0, 100";
			pstmt = connecion.prepareStatement(sql);
			
			// 4. Parameter Mapping
			pstmt.setString(1, "%"+ name +"%");
			pstmt.setString(2, "%"+ name +"%");
			
			// 5. SQL 실행
			rs =pstmt.executeQuery();		
			
			// 6. 결과처리
			while(rs.next()) {
				Long no =  rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				
				result.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
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

	public List<EmployeeVo> findBySalary(Long minSalary, Long maxSalary) {
		
		List<EmployeeVo> result = new ArrayList<>();
		Connection connecion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver 로딩(JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.10.40:3306/employees?charset=utf8";
			connecion = DriverManager.getConnection(url, ID, PASSWORD);
			
			// 3. SQL 준비
			// limit 100, 이클립스 버퍼 크기
			String sql = 
					"   select a.emp_no," +
					"          a.first_name," +
					"          a.last_name," +
					"          b.salary" +
					"     from employees a, salaries b" +
					"    where a.emp_no = b.emp_no" +
					"      and b.to_date = '9999-01-01'" +
					"      and b.salary >= ?" +
					"      and b.salary <= ?" +
					" order by b.salary desc";
			pstmt = connecion.prepareStatement(sql);
			
			// 4. Parameter Mapping
			pstmt.setLong(1, minSalary);
			pstmt.setLong(2, maxSalary);
			
			// 5. SQL 실행
			rs =pstmt.executeQuery();		
			
			// 6. 결과처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Long salary = rs.getLong(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setSalary(salary);
				
				result.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
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
	
}
