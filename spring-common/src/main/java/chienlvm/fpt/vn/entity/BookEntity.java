package chienlvm.fpt.vn.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TB_BOOK", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "BOOK_ID_UK", columnNames = { "BOOK_ID", "AUTHOR_ID", "TYPE_BOOK_ID" }) })
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID", nullable = false)
	private Long bookId;

	@Column(name = "BOOK_NAME", nullable = false)
	private String bookName;

	@Column(name = "AUTHOR_ID", nullable = false)
	private String authorId;

	@Column(name = "BOOK_IMG", nullable = false)
	private String bookImg;

	@Column(name = "BOOK_THUMB_IMG", nullable = false)
	private String bookThumbImg;

	@Column(name = "BOOK_DESCRIBE", nullable = false)
	private String bookDescribe;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "AUTHOR_ID", insertable=false, updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	private AuthorEntity bookAuthorEntity;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_BOOK_ID", insertable=false, updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	private BookType bookType;

	@Column(name = "PUBLISH_YEAR", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date publishYear;

	@Column(name = "TYPE_BOOK_ID")
	private Long typeBookId;

	@Column(name = "DEL_F", nullable = true)
	private Long delF;

	@JsonIgnore
	@Column(name = "DEL_DT", nullable = true)
	private Date delDt;

	@JsonIgnore
	@Column(name = "UPDT_USER_ID", nullable = true)
	private Long upDtUserId;

	@JsonIgnore
	@Column(name = "CRT_DT", nullable = true)
	@CreationTimestamp
	private Date crtDt;

	@JsonIgnore
	@Column(name = "UPDT_DT", nullable = true)
	@CreationTimestamp
	private Date updtDt;
	
	@Transient
	private String linkPC;
	
	public String getLinkPC() {
		return linkPC;
	}

	public void setLinkPC(String linkPC) {
		this.linkPC = linkPC;
	}

	public Long getBookId() {
		return bookId;
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

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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


	public Long getTypeBookId() {
		return typeBookId;
	}

	public void setTypeBookId(Long typeBookId) {
		this.typeBookId = typeBookId;
	}

	public BookEntity() {
		super();
	}

	public BookEntity(Long bookId, String bookName, String authorId, String bookImg, String bookThumbImg,
			String bookDescribe, AuthorEntity bookAuthorEntity, BookType bookType, Date publishYear, Long typeBookId,
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