package chienlvm.fsoft.vn.dto.request;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class BookDtoRequest {
	@Id
	private long bookId;

	@NotNull(message = "error.book.notnull")
	private String bookName;

	@NotEmpty(message = "error.authorId.notempty")
	private String authorId;
	@NotEmpty(message = "error.bookDescribe.notempty")
	private String bookDescribe;

	@NotNull(message = "error.book.typeBookId")
	private Long typeBookId;

	@NotNull(message = "error.book.publishYear")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publishYear;

	private long delF;
	private MultipartFile bookImg;
	private MultipartFile bookImgThumb;

	public Long getTypeBookId() {
		return typeBookId;
	}

	public void setTypeBookId(Long typeBookId) {
		this.typeBookId = typeBookId;
	}

	public Date getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Date publishYear) {
		this.publishYear = publishYear;
	}

	public long getDelF() {
		return delF;
	}

	public void setDelF(long delF) {
		this.delF = delF;
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

	public String getBookDescribe() {
		return bookDescribe;
	}

	public void setBookDescribe(String bookDescribe) {
		this.bookDescribe = bookDescribe;
	}

	public MultipartFile getBookImg() {
		return bookImg;
	}

	public void setBookImg(MultipartFile bookImg) {
		this.bookImg = bookImg;
	}

	public MultipartFile getBookImgThumb() {
		return bookImgThumb;
	}

	public void setBookImgThumb(MultipartFile bookImgThumb) {
		this.bookImgThumb = bookImgThumb;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

}
