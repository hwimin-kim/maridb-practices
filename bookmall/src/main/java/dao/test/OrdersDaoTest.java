package dao.test;

import java.util.List;
import dao.OrdersDao;
import vo.OrdersVo;

public class OrdersDaoTest {

	public static void main(String[] args) {
	 // testInsert();
	 // testFindAll();
	 // testInsertByBook();
	 //	testFindByBook();
	}
	
	private static void testFindAll() {
		List<OrdersVo> list = new OrdersDao().findAll();
		for(OrdersVo vo : list)
			System.out.println(vo);
	}
	
	private static void testFindByBook() {
		List<OrdersVo> list = new OrdersDao().findByBook();
		for(OrdersVo vo : list)
			System.out.println(vo);
	}
	
	private static void testInsert() {
		OrdersDao dao = new OrdersDao();
		OrdersVo vo = new OrdersVo();
		
		vo.setOrdersNumber("20220519-0001");
		vo.setPay(3000L);
		vo.setReceive("센텀호텔....");
		vo.setMemberNo(1L);
		dao.insert(vo);
	}
		
	private static void testInsertByBook() {
		OrdersDao dao = new OrdersDao();
		OrdersVo vo = new OrdersVo();
		
		vo.setBookNo(1L);
		vo.setNo(1L);
		vo.setCount(2L);
		dao.insertByBook(vo);
		
		vo.setBookNo(3L);
		vo.setNo(1L);
		vo.setCount(1L);
		dao.insertByBook(vo);
	}
	
	private static void testDeleteAll() {
		new OrdersDao().DeleteAll();
	}
	
	private static void testAutoIncrementReset() {
		new OrdersDao().autoIncrementReset();
	}
	
	private static void testDeleteAllByBook() {
		new OrdersDao().DeleteAllByBook();
	}
	
	private static void testAutoIncrementResetByBook() {
		new OrdersDao().autoIncrementResetByBook();
	}
}
