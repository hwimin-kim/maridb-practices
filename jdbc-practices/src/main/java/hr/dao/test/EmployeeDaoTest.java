package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {
		// testFindByFirstNameOrLastName("ken");
		testFindBySalary(30000L, 60000L);
	}
	
	public static void testFindByFirstNameOrLastName(String name) {
		List<EmployeeVo> list = new EmployeeDao().findByFirstNameOrLastName(name);
		for(EmployeeVo vo : list)
			System.out.println(vo);
	}
	
	public static void testFindBySalary(Long minSalary, Long maxSalary) {
		List<EmployeeVo> list = new EmployeeDao().findBySalary(minSalary, maxSalary);
		for(EmployeeVo vo : list)
			System.out.println(vo);
	}

}
