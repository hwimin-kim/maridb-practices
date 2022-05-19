package dao.test;

import java.util.ArrayList;
import java.util.List;

import dao.CategoryDao;
import dao.MemberDao;
import vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// testInsert();
		// testFindAll();
	}
	private static void testFindAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list)
			System.out.println(vo);
	}
	
	private static void testInsert() {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo = new CategoryVo();
		
		vo.setName("과학");
		dao.insert(vo);
		
		vo.setName("IT");
		dao.insert(vo);
		
		vo.setName("문학");
		dao.insert(vo);
	}
	
	private static void testDeleteAll() {
		new CategoryDao().DeleteAll();
	}
	
	private static void testAutoIncrementReset() {
		new CategoryDao().autoIncrementReset();
	}
	
}
