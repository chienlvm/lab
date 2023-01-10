package chienlvm.fsoft.vn.entity.redis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_type_book", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "TYPE_BOOK_ID", columnNames = { "TYPE_BOOK_ID" }) })
public class TypeBookRds {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TYPE_BOOK_ID", nullable = false)
	private int typeBookId;
	@Column(name = "TYPE_BOOK_NAME", nullable = false)
	private String typeBookName;

	public int getTypeBookId() {
		return typeBookId;
	}

	public void setTypeBookId(int typeBookId) {
		this.typeBookId = typeBookId;
	}

	public String getTypeBookName() {
		return typeBookName;
	}

	public void setTypeBookName(String typeBookName) {
		this.typeBookName = typeBookName;
	}

	public TypeBookRds(int typeBookId, String typeBookName) {
		super();
		this.typeBookId = typeBookId;
		this.typeBookName = typeBookName;
	}

	public TypeBookRds() {
		super();
	}

}
