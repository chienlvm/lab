package chienlvm.fsoft.vn.entity;

public class JwtRequest {
	
	private String username;
	private String password;
	private String remember;
	
	
	
	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String username, String password, String remember) {
		this.setUsername(username);
		this.setPassword(password);
		this.setRemember(remember);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
