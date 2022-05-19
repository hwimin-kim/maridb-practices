package main;

import java.util.List;

import dao.BookDao;
import dao.CartDao;
import dao.CategoryDao;
import dao.MemberDao;
import dao.OrdersDao;
import vo.BookVo;
import vo.CartVo;
import vo.CategoryVo;
import vo.MemberVo;
import vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {
		bookmallReset();
		System.out.println("==================회원===================");
		
		memberInfo();
		System.out.println("================카테고리==================");
		
		categoryInfo();
		System.out.println("===================책===================");
		
		bookInfo();
		System.out.println("===================카트=================");
		
		cartInfo();
		System.out.println("==================주문==================");
		
		orderInfo();
		System.out.println("================주문도서=================");
		
		orderBookInfo();	
	}
	
	private static void bookmallReset() {
		new OrdersDao().DeleteAllByBook();
		new OrdersDao().autoIncrementResetByBook();
		new OrdersDao().DeleteAll();
		new OrdersDao().autoIncrementReset();
		new CartDao().DeleteAll();
		new CartDao().autoIncrementReset();
		new BookDao().DeleteAll();
		new BookDao().autoIncrementReset();
		new CategoryDao().DeleteAll();
		new CategoryDao().autoIncrementReset();
		new MemberDao().DeleteAll();
		new MemberDao().autoIncrementReset();	
	}
	
	private static void memberInfo() {
		new MemberDao().insert("둘리", "123456", "010-0000-0000", "둘리@gmail.com");
		new MemberDao().insert("도우너", "999999", "010-7777-7777", "도우너@gmail.com");
		
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list)
			System.out.println(vo);
	}
	
	private static void categoryInfo() {
		new CategoryDao().insert("과학");
		new CategoryDao().insert("IT");
		new CategoryDao().insert("문학");
		
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list)
			System.out.println(vo);
	}
	
	private static void bookInfo() {
		new BookDao().insert("이것이 MariaDB다", 1000L, 2L);
		new BookDao().insert("러닝 React.JS", 2000L, 2L);
		new BookDao().insert("코스모스", 1000L, 1L);
		
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo: list)
			System.out.println(vo);	
	}
	
	private static void cartInfo() {
		new CartDao().insert(1L, 1L, 2L);
		new CartDao().insert(1L, 3L, 1L);
		
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list)
			System.out.println(vo);
	}
	
	private static void orderInfo() {
		new OrdersDao().insert("20220519-0001", 3000L, "센텀호텔....", 1L);
		
		List<OrdersVo> list = new OrdersDao().findAll();
		for(OrdersVo vo : list)
			System.out.println(vo);
	}
	
	private static void orderBookInfo() {
		new OrdersDao().insertByBook(1L, 1L, 2L);
		new OrdersDao().insertByBook(3L, 1L, 1L);
		
		List<OrdersVo> list = new OrdersDao().findByBook();
		for(OrdersVo vo : list)
			System.out.println(vo.toStringByBook());
	}
}
