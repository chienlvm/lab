package chienlvm.fsoft.vn.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "book")
public class BookEls {
	@Id
	private Long bookId;
	private String bookName;
	private String bookDescribe;
	private String delF;
	private String bookImg;
	private String bookThumbImg;
	private Author author;
	private TypeBook typeBook;

	@Field(type = FieldType.Date, format = DateFormat.basic_date)
	private Date publicYear;
	
	@Field(type = FieldType.Date, format = DateFormat.basic_date)
	private Date createDt;
	public BookEls() {
		super();
	}

	public BookEls(Long bookId, String bookName, String bookDescribe, String delF, String bookImg, String bookThumbImg,
			Author author, TypeBook typeBook, Date publicYear, Date createDt) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookDescribe = bookDescribe;
		this.delF = delF;
		this.bookImg = bookImg;
		this.bookThumbImg = bookThumbImg;
		this.author = author;
		this.typeBook = typeBook;
		this.publicYear = publicYear;
		this.createDt = createDt;
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

	public String getDelF() {
		return delF;
	}

	public void setDelF(String delF) {
		this.delF = delF;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public String getBookThumbImg() {
		return bookThumbImg;
	}

	public void setBookThumbImg(String bookThumbImg) {
		this.bookThumbImg = bookThumbImg;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public TypeBook getTypeBook() {
		return typeBook;
	}

	public void setTypeBook(TypeBook typeBook) {
		this.typeBook = typeBook;
	}

	public Date getPublicYear() {
		return publicYear;
	}

	public void setPublicYear(Date publicYear) {
		this.publicYear = publicYear;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Override
	public String toString() {
		return "BookEls [bookId=" + bookId + ", bookName=" + bookName + ", bookDescribe=" + bookDescribe + ", delF="
				+ delF + ", bookImg=" + bookImg + ", bookThumbImg=" + bookThumbImg + ", author=" + author
				+ ", typeBook=" + typeBook + ", publicYear=" + publicYear + ", createDt=" + createDt + "]";
	}
	

}
