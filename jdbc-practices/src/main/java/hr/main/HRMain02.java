package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class HRMain02 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("salary[min max]>");
		Long minSalary = scanner.nextLong();
		Long maxSalary = scanner.nextLong();
		
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVo> list = dao.findBySalary(minSalary, maxSalary);
		
		// no:firstName:lastName:salary
		// order by desc
		for(EmployeeVo vo : list) 
			System.out.println(vo.getNo() + ":" + vo.getFirstName() + ":" + vo.getLastName() + ":" + vo.getSalary());
		
		scanner.close();
	}

}
