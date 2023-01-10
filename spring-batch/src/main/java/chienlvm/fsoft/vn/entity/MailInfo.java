package chienlvm.fsoft.vn.entity;

public class MailInfo {
	private Long userId;
	private String useName;
	private Long bookId;
	private String bookName;
	private String bookDescribe;
	private String bookAuthor;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
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
	@Override
	public String toString() {
		return "MailInfo [userId=" + userId + ", useName=" + useName + ", bookId=" + bookId + ", bookName=" + bookName
				+ ", bookDescribe=" + bookDescribe + ", bookAuthor=" + bookAuthor + "]";
	}
}
