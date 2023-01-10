package chienlvm.fsoft.vn.entity;

public class TypeBookEls {
	private Long typeBookId;
	private String typeBookName;
	private int total;
	public TypeBookEls() {
		super();
	}
	public TypeBookEls(Long typeBookId, String typeBookName, int total) {
		super();
		this.typeBookId = typeBookId;
		this.typeBookName = typeBookName;
		this.total = total;
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
	
}
