package bookshop.dao.test;

import java.util.List;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		BookVo vo = new BookVo();
		
		vo.setTitle("트와일라잇");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("뉴문");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("이클립스");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("브레이킹던");
		vo.setAuthorNo(1L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("아리랑");
		vo.setAuthorNo(2L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("젊은그들");
		vo.setAuthorNo(3L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("아프니깐 청춘이다");
		vo.setAuthorNo(4L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("귀천");
		vo.setAuthorNo(5L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("태백산맥");
		vo.setAuthorNo(6L);
		vo.setStateCode("재고있음");
		testInsert(vo);

		vo.setTitle("풀하우스");
		vo.setAuthorNo(7L);
		vo.setStateCode("재고있음");
		testInsert(vo);
		
		int count = testFindAll();
		if(count == 10) {
			System.out.println("성공");
			// new BookDao().delete();
		}
		else
			System.out.println("실패");
	}

	private static int testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		return list.size();
		
	}

	public static void testInsert(BookVo vo) {
		new BookDao().insert(vo);
	}
}
