package chienlvm.fpt.vn.dto;

import java.util.Date;

/**
 * Book type dto
 * 
 * @author chienlvm
 *
 */

public class BookTypeDto {
	private Long typeBookId;
	private String typeBookName;
	private boolean delF;
	private Date delDt;
	private String updtUserId;
	private Date crtDt;
	private Date updtDt;

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

	public boolean isDelF() {
		return delF;
	}

	public void setDelF(boolean delF) {
		this.delF = delF;
	}

	public String getUpdtUserId() {
		return updtUserId;
	}

	public void setUpdtUserId(String updtUserId) {
		this.updtUserId = updtUserId;
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

	public BookTypeDto() {
		super();
	}

	public BookTypeDto(Long typeBookId, String typeBookName, boolean delF, Date delDt, String updtUserId, Date crtDt,
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

}
