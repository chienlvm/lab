package chienlvm.fsoft.vn.entity.redis;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonFormat;

@RedisHash(value = "book", timeToLive = 600)
public class BookRds {

	@Id
	@Indexed
	private Long bookId;
	private String bookName;
	private String authorId;
	private String bookImg;
	private String bookThumbImg;
	private String bookDescribe;

	private AuthorRds authorRds;

	private Long delF;

	private Date delDt;

	private Long upDtUserId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date crtDt;

	private Date updtDt;

	private TypeBookRds typeBook;

	private int typeBookId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date publishYear;

	private String redisMessage;

	public String getRedisMessage() {
		return redisMessage;
	}

	public void setRedisMessage(String redisMessage) {
		this.redisMessage = redisMessage;
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


	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public int getTypeBookId() {
		return typeBookId;
	}

	public void setTypeBookId(int typeBookId) {
		this.typeBookId = typeBookId;
	}

	public Date getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Date publishYear) {
		this.publishYear = publishYear;
	}

	public BookRds() {
		super();
	}

	public AuthorRds getAuthorRds() {
		return authorRds;
	}

	public void setAuthorRds(AuthorRds authorRds) {
		this.authorRds = authorRds;
	}

	public TypeBookRds getTypeBook() {
		return typeBook;
	}

	public void setTypeBook(TypeBookRds typeBook) {
		this.typeBook = typeBook;
	}

	public BookRds(Long bookId, String bookName, String authorId, String bookImg, String bookThumbImg,
			String bookDescribe, AuthorRds authorRds, Long delF, Date delDt, Long upDtUserId, Date crtDt, Date updtDt,
			TypeBookRds typeBook, int typeBookId, Date publishYear, String redisMessage) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorId = authorId;
		this.bookImg = bookImg;
		this.bookThumbImg = bookThumbImg;
		this.bookDescribe = bookDescribe;
		this.authorRds = authorRds;
		this.delF = delF;
		this.delDt = delDt;
		this.upDtUserId = upDtUserId;
		this.crtDt = crtDt;
		this.updtDt = updtDt;
		this.typeBook = typeBook;
		this.typeBookId = typeBookId;
		this.publishYear = publishYear;
		this.redisMessage = redisMessage;
	}

}
