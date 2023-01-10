package chienlvm.fsoft.vn.entity;

public class MailEntity {
	private Long userId;
	private Long bookId;
	private String email;
	private String useName;
	private String bookName;
	private String bookDescribe;
	private String bookAuthor;
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDescribe() {
		return bookDescribe;
	}
	public void setBookDescribe(String bookDescribe) {
		this.bookDescribe = bookDescribe;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	private java.util.Date scrapDt;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public java.util.Date getScrapDt() {
		return scrapDt;
	}
	public void setScrapDt(java.util.Date date) {
		this.scrapDt = date;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	@Override
	public String toString() {
		return "MailEntity [userId=" + userId + ", bookId=" + bookId + ", email=" + email + ", useName=" + useName
				+ ", bookName=" + bookName + ", bookDescribe=" + bookDescribe + ", bookAuthor=" + bookAuthor
				+ ", scrapDt=" + scrapDt + "]";
	}
}
