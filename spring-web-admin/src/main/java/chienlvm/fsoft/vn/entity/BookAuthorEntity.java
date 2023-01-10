package chienlvm.fsoft.vn.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_book_author", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "AUTHOR_ID", columnNames = { "AUTHOR_ID" }) })
public class BookAuthorEntity {

	@Id
	@GeneratedValue
	@Column(name = "AUTHOR_ID", nullable = false)
	private Long authorId;
	@Column(name = "AUTHOR_NAME", nullable = false)
	private String authorName;
	@Column(name = "DATE_OF_BIRTH", nullable = false)
	private String dateOfBirth;
	@Column(name = "AUTHOR_DESCRIBE", nullable = false)
	private String authorDescribe;

	@Column(name = "DEL_F", nullable = true)
	private Long delF;

	@Column(name = "DEL_DT", nullable = true, updatable = false)
	@CreationTimestamp
	private Date delDt;
	@JsonIgnore
	@Column(name = "UPDT_USER_ID", nullable = true)
	private Long upDtUserId;
	@JsonIgnore
	@Column(name = "CRT_DT", nullable = true, updatable = false)
	@CreationTimestamp
	private Date crtDt;
	@JsonIgnore
	@Column(name = "UPDT_DT", nullable = true, updatable = false)
	@CreationTimestamp
	private Date updtDt;

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

}