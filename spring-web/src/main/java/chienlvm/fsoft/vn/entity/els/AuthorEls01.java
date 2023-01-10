package chienlvm.fsoft.vn.entity.els;


public class AuthorEls01 {
	private Long authorId;
	private String authorName;
	public AuthorEls01() {
		super();
	}
	public AuthorEls01(Long authorId, String authorName) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
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
	
}
