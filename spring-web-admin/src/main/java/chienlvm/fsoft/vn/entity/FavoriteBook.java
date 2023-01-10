package chienlvm.fsoft.vn.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tb_scrap")
public class FavoriteBook {
	@Id
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "BOOK_ID", nullable = false)
	private Long bookId;

	@Column(name = "SCRAP_DT", nullable = false, updatable = false)
	@CreationTimestamp
	private Date scrapDt;

	@Column(name = "DEL_F", nullable = true)
    private Long delF;

	@Column(name = "DEL_DT", nullable = true, updatable = false)
	@CreationTimestamp
	private Date delDt;

	@Column(name = "UPDT_USER_ID", nullable = true)
	private Long upDtUserId;

	@Column(name = "CRT_DT", nullable = true, updatable = false)
	@CreationTimestamp
	private Date crtDt;
	
	@Column(name = "UPDT_DT", nullable = true, updatable = false)
	@CreationTimestamp
	private Date updtDt;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Date getScrapDt() {
		return scrapDt;
	}

	public void setScrapDt(Date scrapDt) {
		this.scrapDt = scrapDt;
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
	@PrePersist
	private void onCreate() {
		this.scrapDt = new Date();
		this.delF = (long) 0;
	}
	
}