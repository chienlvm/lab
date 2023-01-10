package chienlvm.fsoft.vn.dto.request;

import java.util.Date;

public class AuthorRequest {
	private Long authorId;
	private String authorName;
	private Date dateOfBirth;
	private String authorDescribe;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAuthorDescribe() {
		return authorDescribe;
	}

	public void setAuthorDescribe(String authorDescribe) {
		this.authorDescribe = authorDescribe;
	}

	public AuthorRequest(Long authorId, String authorName, Date dateOfBirth, String authorDescribe) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.dateOfBirth = dateOfBirth;
		this.authorDescribe = authorDescribe;
	}

	public AuthorRequest() {
		super();
	}

}
