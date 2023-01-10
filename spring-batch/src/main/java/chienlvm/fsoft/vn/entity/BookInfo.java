package chienlvm.fsoft.vn.entity;

public class BookInfo {
	private Long bookId;
	private String bookName;
	private String bookDescribe;
	private String bookAuthor;
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
	public BookInfo(Long bookId, String bookName, String bookDescribe, String bookAuthor) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookDescribe = bookDescribe;
		this.bookAuthor = bookAuthor;
	}
	@Override
	public String toString() {
		return "BookInfo [bookId=" + bookId + ", bookName=" + bookName + ", bookDescribe=" + bookDescribe
				+ ", bookAuthor=" + bookAuthor + "]";
	}
}
