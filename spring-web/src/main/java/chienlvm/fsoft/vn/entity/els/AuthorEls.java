package chienlvm.fsoft.vn.entity.els;

public class AuthorEls {
	private Long authorId;
	private String authorName;
	private String linkPC;
	private int total;
	public AuthorEls() {
		super();
	}
	public AuthorEls(Long authorId, String authorName, int total, String linkPC) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.total = total;
		this.linkPC = linkPC;
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
	public String getLinkPC() {
		return linkPC;
	}
	public void setLinkPC(String linkPC) {
		this.linkPC = linkPC;
	}
	
}
