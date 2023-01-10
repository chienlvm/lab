package chienlvm.fsoft.vn.entity.els;
public class TypeBookEls {
	private Long typeBookId;
	private String typeBookName;
	private int total;
	private String linkPC;
	public TypeBookEls() {
		super();
	}
	public TypeBookEls(Long typeBookId, String typeBookName, int total, String linkPC) {
		super();
		this.typeBookId = typeBookId;
		this.typeBookName = typeBookName;
		this.total = total;
		this.linkPC = linkPC;
	}
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
