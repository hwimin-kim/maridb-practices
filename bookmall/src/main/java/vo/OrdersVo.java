package vo;

public class OrdersVo {
	private Long no;
	private String ordersNumber;
	private Long pay;
	private String receive;
	private Long memberNo;
	private String memberName;
	private String memberEmail;
	private Long bookNo;
	private Long count;
	private String bookTitle;
	private Long totalPay;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrdersNumber() {
		return ordersNumber;
	}
	public void setOrdersNumber(String ordersNumber) {
		this.ordersNumber = ordersNumber;
	}
	public Long getPay() {
		return pay;
	}
	public void setPay(Long pay) {
		this.pay = pay;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
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
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Long getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(Long totalPay) {
		this.totalPay = totalPay;
	}
	@Override
	public String toString() {
		return "주문번호:" + ordersNumber + "\r\n이름:" + memberName + "(" + memberEmail + ")\r\n비용:" + pay + "\r\n주소:" + receive;
	}
		
	public String toStringByBook() {
		return "책 번호:" + bookNo + ", 책 제목:" + bookTitle + ", 수량:" + count +  "권, 비용:" + totalPay;
	}
}
