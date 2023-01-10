package chienlvm.fpt.vn.dto;

import java.util.Date;

import chienlvm.fpt.vn.entity.AuthorEntity;
import chienlvm.fpt.vn.entity.BookType;

public class BookDto {
	private int bookId;
	private String bookName;

	private String authorId;

	private String bookImg;

	private String bookThumbImg;

	private String bookDescribe;

	private AuthorEntity bookAuthorEntity;

	private BookType bookType;

	private Date publishYear;

	private String typeBookId;

	private Long delF;

	private Date delDt;
	private Long upDtUserId;

	private Date crtDt;
	private Date updtDt;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
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

	public String getBookDescribe() {
		return bookDescribe;
	}

	public void setBookDescribe(String bookDescribe) {
		this.bookDescribe = bookDescribe;
	}

	public AuthorEntity getBookAuthorEntity() {
		return bookAuthorEntity;
	}

	public void setBookAuthorEntity(AuthorEntity bookAuthorEntity) {
		this.bookAuthorEntity = bookAuthorEntity;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public Date getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Date publishYear) {
		this.publishYear = publishYear;
	}

	public String getTypeBookId() {
		return typeBookId;
	}

	public void setTypeBookId(String typeBookId) {
		this.typeBookId = typeBookId;
	}

	public Long getDelF() {
		return delF;
	}

	public void setDelF(Long delF) {
		this.delF = delF;
	}

	public Date getDelDt() {
		return delDt;
	}

	public void setDelDt(Date delDt) {
		this.delDt = delDt;
	}

	public Long getUpDtUserId() {
		return upDtUserId;
	}

	public void setUpDtUserId(Long upDtUserId) {
		this.upDtUserId = upDtUserId;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public Date getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(Date updtDt) {
		this.updtDt = updtDt;
	}

	public BookDto() {
		super();
	}

	public BookDto(int bookId, String bookName, String authorId, String bookImg, String bookThumbImg,
			String bookDescribe, AuthorEntity bookAuthorEntity, BookType bookType, Date publishYear, String typeBookId,
			Long delF, Date delDt, Long upDtUserId, Date crtDt, Date updtDt) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorId = authorId;
		this.bookImg = bookImg;
		this.bookThumbImg = bookThumbImg;
		this.bookDescribe = bookDescribe;
		this.bookAuthorEntity = bookAuthorEntity;
		this.bookType = bookType;
		this.publishYear = publishYear;
		this.typeBookId = typeBookId;
		this.delF = delF;
		this.delDt = delDt;
		this.upDtUserId = upDtUserId;
		this.crtDt = crtDt;
		this.updtDt = updtDt;
	}

}
