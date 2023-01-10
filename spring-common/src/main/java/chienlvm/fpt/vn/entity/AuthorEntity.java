package chienlvm.fpt.vn.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_book_author", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "AUTHOR_ID", columnNames = { "AUTHOR_ID" }) })
public class AuthorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTHOR_ID", nullable = false)
	private Long authorId;

	@Column(name = "AUTHOR_NAME", nullable = false)
	private String authorName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE_OF_BIRTH", nullable = false)
	private Date dateOfBirth;

	@Column(name = "AUTHOR_DESCRIBE", nullable = false)
	private String authorDescribe;

	@Column(name = "DEL_F", nullable = true)
	private Long delF;

	@JsonIgnore
	@Column(name = "DEL_DT", nullable = true)
	private Date delDt;

	@JsonIgnore
	@Column(name = "UPDT_USER_ID", nullable = true)
	private Long upDtUserId;

	@JsonIgnore
	@Column(name = "CRT_DT", nullable = true, updatable = false)
	@CreationTimestamp
	private Date crtDt;

	@JsonIgnore
	@Column(name = "UPDT_DT", nullable = true)
	private Date updtDt;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn(name = "AUTHOR_ID", insertable = false, updatable = false)
	private List<BookEntity> bookEntity;

	@Transient
	private boolean isExistsBook;

	public boolean isExistsBook() {
		return isExistsBook;
	}

	public void setExistsBook(boolean isExistsBook) {
		this.isExistsBook = isExistsBook;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDescribe() {
		return authorDescribe;
	}

	public void setAuthorDescribe(String authorDescribe) {
		this.authorDescribe = authorDescribe;
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

	public AuthorEntity() {
		super();
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public AuthorEntity(Long authorId, String authorName, Date dateOfBirth, String authorDescribe, Long delF,
			Date delDt, Long upDtUserId, Date crtDt, Date updtDt) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.dateOfBirth = dateOfBirth;
		this.authorDescribe = authorDescribe;
		this.delF = delF;
		this.delDt = delDt;
		this.upDtUserId = upDtUserId;
		this.crtDt = crtDt;
		this.updtDt = updtDt;
	}

	@PrePersist
	private void onCreate() {
		this.crtDt = new Date();
		this.delF = (long) 0;
	}

	public List<BookEntity> getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(List<BookEntity> bookEntity) {
		this.bookEntity = bookEntity;
	}

}
