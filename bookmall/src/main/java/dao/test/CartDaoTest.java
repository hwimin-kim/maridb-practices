package dao.test;

import java.util.List;

import dao.CartDao;
import dao.MemberDao;
import vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		// testInsert();
		// testFindAll();
	}
	
	private static void testFindAll() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list)
			System.out.println(vo);
	}

	private static void testInsert() {
		CartDao dao = new CartDao();
		CartVo vo = new CartVo();
		
		vo.setMemberNo(1L);
		vo.setBookNo(1L);
		vo.setCount(2L);
		dao.insert(vo);

		vo.setMemberNo(1L);
		vo.setBookNo(3L);
		vo.setCount(1L);
		dao.insert(vo);
	}
	
	private static void testDeleteAll() {
		new CartDao().DeleteAll();
	}
	
	private static void testAutoIncrementReset() {
		new CartDao().autoIncrementReset();
	}
}
