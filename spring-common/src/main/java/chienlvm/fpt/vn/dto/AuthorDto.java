package chienlvm.fpt.vn.dto;

import java.util.Date;
/**
 * 
 * @author chien
 *
 */

public class AuthorDto {
	private int authorId;
	private String authorName;

	private String dateOfBirth;

	private String authorDescribe;

	private Long delF;

	private Date delDt;

	private Long upDtUserId;

	private Date crtDt;

	private Date updtDt;

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
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

	public AuthorDto() {
		super();
	}

	public AuthorDto(Integer authorId, String authorName, String dateOfBirth, String authorDescribe, Long delF,
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

}
