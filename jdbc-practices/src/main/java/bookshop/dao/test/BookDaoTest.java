package bookshop.dao.test;

import java.util.List;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		// testInsert();
		testUpdate();
		//testFindAll();
	}

	private static void testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list)
			System.out.println(vo);	
	}
	
	private static void testUpdate() {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo.setNo(1L);
		vo.setStateCode("대여중");
		
		dao.update(vo);
		
		// test 성공 여부
		vo = dao.findByNo(1L);
		if("대여중".equals(vo.getStateCode()))
			System.out.println("성공");
	}

	public static void testInsert() {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		
		vo.setTitle("트와일라잇");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("뉴문");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("이클립스");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("브레이킹던");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("아리랑");
		vo.setAuthorNo(2L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("젊은그들");
		vo.setAuthorNo(3L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("아프니깐 청춘이다");
		vo.setAuthorNo(4L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("귀천");
		vo.setAuthorNo(5L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("태백산맥");
		vo.setAuthorNo(6L);
		vo.setStateCode("재고있음");
		dao.insert(vo);

		vo.setTitle("풀하우스");
		vo.setAuthorNo(7L);
		vo.setStateCode("재고있음");
		dao.insert(vo);		
	}
}
