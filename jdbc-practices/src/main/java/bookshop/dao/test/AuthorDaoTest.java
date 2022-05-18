package bookshop.dao.test;

import bookshop.dao.AuthorDao;
import bookshop.vo.AuthorVo;

public class AuthorDaoTest {

	public static void main(String[] args) {
		AuthorVo vo =new AuthorVo();
		vo.setName("스테파니메이어");
		testInsert(vo);

		vo.setName("조정래");
		testInsert(vo);
		
		vo.setName("김동인");
		testInsert(vo);
		
		vo.setName("김난도");
		testInsert(vo);
		
		vo.setName("천상병");
		testInsert(vo);
		
		vo.setName("조정래");
		testInsert(vo);
		
		vo.setName("원수연");
		testInsert(vo);
	}

	public static void testInsert(AuthorVo vo) {
		boolean result = new AuthorDao().insert(vo);
		if(result)
			System.out.println("성공");
		else
			System.out.println("실패");
	}
}
