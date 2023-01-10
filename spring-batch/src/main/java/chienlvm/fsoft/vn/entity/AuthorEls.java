package chienlvm.fsoft.vn.entity;

public class AuthorEls {
	private Long authorId;
	private String authorName;
	private int total;
	public AuthorEls() {
		super();
	}
	public AuthorEls(Long authorId, String authorName, int total) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.total = total;
	}
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
