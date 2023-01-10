package chienlvm.fsoft.vn.entity.els;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(indexName = "book")
public class BookEls {
	@Id
	private Long bookId;
	private String bookName;
	private String bookDescribe;
	private String delF;
	private String bookImg;
	private String bookThumbImg;
	private AuthorEls01 author;
	private TypeBook typeBook;
	private String linkPC;
	private boolean isFavorite = false;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Field(type = FieldType.Auto)
	private Date publicYear;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Field(type = FieldType.Auto)
	private Date createDt;

	public BookEls() {
		super();
	}

	public BookEls(Long bookId, String bookName, String bookDescribe, String delF, String bookImg, String bookThumbImg,
			AuthorEls01 author, TypeBook typeBook, String linkPC, boolean isFavorite, Date publicYear, Date createDt) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookDescribe = bookDescribe;
		this.delF = delF;
		this.bookImg = bookImg;
		this.bookThumbImg = bookThumbImg;
		this.author = author;
		this.typeBook = typeBook;
		this.linkPC = linkPC;
		this.isFavorite = isFavorite;
		this.publicYear = publicYear;
		this.createDt = createDt;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public void setAuthor(AuthorEls01 author) {
		this.author = author;
	}

	public BookEls(BookEls content) {
		// TODO Auto-generated constructor stub
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

	public AuthorEls01 getAuthor() {
		return author;
	}

	public void setAuthorEls01(AuthorEls01 author) {
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

	public String getLinkPC() {
		return linkPC;
	}

	public void setLinkPC(String linkPC) {
		this.linkPC = linkPC;
	}

}
