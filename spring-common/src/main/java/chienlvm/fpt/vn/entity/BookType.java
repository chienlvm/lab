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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Book type entity
 * 
 * @author chienlvm
 *
 */

@Entity
@Table(name = "TB_TYPE_BOOK", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "TYPE_BOOK_ID", columnNames = { "TYPE_BOOK_ID" }) })
public class BookType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TYPE_BOOK_ID", nullable = false)
	private Long typeBookId;

	@Column(name = "TYPE_BOOK_NAME", nullable = false)
	private String typeBookName;

	@Column(name = "DEL_F", nullable = false)
	private Long delF;

	@JsonIgnore
	@Column(name = "DEL_DT", nullable = true)
	private Date delDt;

	@JsonIgnore
	@Column(name = "UPDT_USER_ID", nullable = true)
	private Long updtUserId;

	@JsonIgnore
	@Column(name = "CRT_DT", nullable = true, updatable = false)
	private Date crtDt;

	@JsonIgnore
	@Column(name = "UPDT_DT", nullable = true)
	private Date updtDt;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn(name = "TYPE_BOOK_ID", insertable = false, updatable = false)
	private List<BookEntity> bookEntity;

	@Transient
	private boolean isExistsBook;

	public boolean isExistsBook() {
		return isExistsBook;
	}

	public void setExistsBook(boolean isExistsBook) {
		this.isExistsBook = isExistsBook;
	}

	public Long getTypeBookId() {
		return typeBookId;
	}

	public void setTypeBookId(Long typeBookId) {
		this.typeBookId = typeBookId;
	}

	public String getTypeBookName() {
		return typeBookName;
	}

	public void setTypeBookName(String typeBookName) {
		this.typeBookName = typeBookName;
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

	public Long getUpdtUserId() {
		return updtUserId;
	}

	public void setUpdtUserId(Long updtUserId) {
		this.updtUserId = updtUserId;
	}

	public BookType() {
		super();
	}

	public List<BookEntity> getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(List<BookEntity> bookEntity) {
		this.bookEntity = bookEntity;
	}

	public BookType(Long typeBookId, String typeBookName, Long delF, Date delDt, Long updtUserId, Date crtDt,
			Date updtDt) {
		super();
		this.typeBookId = typeBookId;
		this.typeBookName = typeBookName;
		this.delF = delF;
		this.delDt = delDt;
		this.updtUserId = updtUserId;
		this.crtDt = crtDt;
		this.updtDt = updtDt;
	}

	@PrePersist
	private void onCreate() {
		this.crtDt = new Date();
		this.delF = (long) 0;
	}
}
