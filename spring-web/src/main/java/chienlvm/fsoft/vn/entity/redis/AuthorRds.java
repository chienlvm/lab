package chienlvm.fsoft.vn.entity.redis;

import java.util.Date;

public class AuthorRds {
	private Long authorId;
	private String dateOfBirth;
	private String authorDescribe;
	private String authorName;
	private Long delF;
	private Date delDt;
	private Long upDtUserId;
	private Date crtDt;
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
	public AuthorRds() {
		super();
	}
	public AuthorRds(Long authorId, String dateOfBirth, String authorDescribe, String authorName, Long delF, Date delDt,
			Long upDtUserId, Date crtDt, Date updtDt) {
		super();
		this.authorId = authorId;
		this.dateOfBirth = dateOfBirth;
		this.authorDescribe = authorDescribe;
		this.authorName = authorName;
		this.delF = delF;
		this.delDt = delDt;
		this.upDtUserId = upDtUserId;
		this.crtDt = crtDt;
		this.updtDt = updtDt;
	}

}