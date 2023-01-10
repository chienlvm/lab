package chienlvm.fsoft.vn.dto.response;
/**
 * User dto
 * @author chien
 *
 */
public class UserDto {
	private String message;
	private String status;
	public static final String STATUS_OK = "SUCCESS";
	public static final String STATUS_FAILED = "FAILED";
	
	public UserDto(String status, String message) {
		this.status = status;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static String getStatusOk() {
		return STATUS_OK;
	}
	public static String getStatusFailed() {
		return STATUS_FAILED;
	}
}
