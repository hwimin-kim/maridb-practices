package bookshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class Main {

	public static void main(String[] args) {
		displayBookInfo();
		  
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		Long no = scanner.nextLong();
		scanner.close();
		 		
		new BookDao().update(no, "대여중");
		  
		displayBookInfo();	 
	}
	private static void displayBookInfo() {
		List<BookVo> list = new BookDao().findAll();
		
		System.out.println("*****도서 정보 출력하기******"); 
		for(BookVo vo : list) 
			System.out.println("["+ vo.getNo() +"] 책 제목:" + vo.getTitle() +" 작가:" + vo.getAuthorName() +" 대여 유무:" +vo.getStateCode());	
	
		System.out.println("************************"); 
	}
}
