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
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "book_id", nullable = false)
	private Long bookId;

	@Column(name = "scrap_dt", nullable = false)
	@CreationTimestamp
	private Date scrapDt;

	@Column(name = "del_f", nullable = true)
	private Long delF;

	@Column(name = "del_dt", nullable = true)
	@CreationTimestamp
	private Date delDt;

	@Column(name = "crt_dt", nullable = true)
	@CreationTimestamp
	private Date crtDt;

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

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@PrePersist
	private void onCreate() {
		this.scrapDt = new Date();
		this.delF = (long) 0;
	}
}
