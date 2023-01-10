package chienlvm.fsoft.vn.entity;

public class TypeBook {
	private Long typeBookId;
	private String typeBookName;
	public TypeBook() {
		super();
	}
	public TypeBook(Long typeBookId, String typeBookName) {
		super();
		this.typeBookId = typeBookId;
		this.typeBookName = typeBookName;
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
	
}
