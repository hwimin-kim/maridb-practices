package dao.test;

import java.util.List;

import dao.BookDao;
import dao.MemberDao;
import vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		// testInsert();
		// testFindAll();
	}
	
	private static void testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo: list)
			System.out.println(vo);
	}
	
	private static void testInsert() {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		
		vo.setTitle("이것이 MariaDB다");
		vo.setPrice(1000L);
		vo.setCategoryNo(1L);
		dao.insert(vo);
		
		vo.setTitle("러닝 React.JS");
		vo.setPrice(2000L);
		vo.setCategoryNo(2L);
		dao.insert(vo);
		
		vo.setTitle("코스모스");
		vo.setPrice(1000L);
		vo.setCategoryNo(3L);
		dao.insert(vo);
	}
	
	private static void testDeleteAll() {
		new BookDao().DeleteAll();
	}
	
	private static void testAutoIncrementReset() {
		new BookDao().autoIncrementReset();
	}
}
