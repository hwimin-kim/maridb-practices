package dao.test;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		// testInsert();
		// testFindAll();
		// testDeleteAll();
		// testAutoIncrementReset();
	}
	
	private static void testInsert() {
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();
		
		vo.setName("둘리");
		vo.setPassword("123456");
		vo.setPhoneNumber("010-0000-0000");
		vo.setEmail("둘리@gmail.com");
		dao.insert(vo);
		
		vo.setName("도우너");
		vo.setPassword("999999");
		vo.setPhoneNumber("010-7777-7777");
		vo.setEmail("도우너@gmail.com");
		dao.insert(vo);
		
	}
	
	private static void testFindAll() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list)
			System.out.println(vo);
	}
	
	private static void testDeleteAll() {
		new MemberDao().DeleteAll();
	}
	
	private static void testAutoIncrementReset() {
		new MemberDao().autoIncrementReset();
	}
}
