package chienlvm.fsoft.vn.dto.response;

/**
 * Method format error
 * 
 * @author chienlvm
 *
 */
public class ApiError {
	private String field;
	private String message;

	public ApiError() {
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiError(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

}
