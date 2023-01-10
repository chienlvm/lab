package chienlvm.fpt.vn.commom;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


public class ResponseDataError {
	private Map<String, Object> meta;
	private String error;

	private ResponseDataError(String error) {
		this.meta = new HashMap<>();
		this.meta.put("httpStatusCode", HttpServletResponse.SC_OK);
		this.meta.put("messageType", MessageType.INFO);
		this.meta.put("messageCode", null);
		this.meta.put("messageContent", null);
		this.error = error;
	}
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
	public static ResponseDataError createWithError(String error) {
		ResponseDataError responseData = new ResponseDataError(error);
		return responseData;
	}


	public void setMeta(String key, Object data) {
		this.meta.put(key, data);
	}

	public void setMeta(int httpStatusCode, String messageType, String messageCode, String messageContent) {
		this.meta.put("httpStatusCode", httpStatusCode);
		this.meta.put("messageType", messageType);
		this.meta.put("messageCode", messageCode);
		this.meta.put("messageContent", messageContent);
	}
	public Object getMeta() {
		return this.meta;
	}

}
