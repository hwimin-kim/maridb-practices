package vo;

public class CartVo {
	private Long memberNo;
	private Long bookNo;
	private Long count;
	private String memberName;
	private String bookTitle;

	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	@Override
	public String toString() {
		return  "이름:" + memberName + ", 책 제목:" + bookTitle + ", 수량:" + count + "권";
	}
	
}
