package bookshop;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.example.Book;
import bookshop.vo.BookVo;

public class Main {

	public static void main(String[] args) {
		displayBookInfo();
		  
		  Scanner scanner = new Scanner(System.in);
		  System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		  int num = scanner.nextInt();
		  scanner.close();
		  
		  // (1) 입력된 번호에 맞는 책을 찾아 대여 되었음(상태코드=0)을 체크 합니다.
//		  for(Book book : books) {
//			  if(num == book.getBookNo()) {
//				  book.rent();
//				  break;
//			  }
//		  }
		  
		  // (2) Book 객체의 정보를 출력 System.out.println("*****도서 정보 출력하기******");
		  // System.out.println("*****도서 정보 출력하기******");
		  // displayBookInfo();
		 
	}
	private static void displayBookInfo() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) 
			System.out.println("["+ vo.getNo() +"] 책 제목:" + vo.getTitle() +" 작가:" + vo.getAuthorName() +" 대여 유무:" +vo.getStateCode());	
	}

}
