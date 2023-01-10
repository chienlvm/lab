package chienlvm.fsoft.vn.dto.response;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import chienlvm.fpt.vn.commom.MessageType;

public class ResponseData {
	private Map<String, Object> meta;
	private Map<String, Object> data;

	private ResponseData() {
		this.meta = new HashMap<>();
		this.data = new HashMap<>();
		this.meta.put("httpStatusCode", HttpServletResponse.SC_OK);
		this.meta.put("messageType", MessageType.INFO);
		this.meta.put("messageCode", null);
		this.meta.put("messageContent", null);
	}

	public static ResponseData create() {
		ResponseData responseData = new ResponseData();
		return responseData;
	}

	public Object getData() {
		return this.data;
	}
	public void setData(String key, Object data) {
		this.data.put(key, data);
	}

	public Object getDataByKey(String key) {
		return this.data.get(key);
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
