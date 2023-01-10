package chienlvm.fpt.vn.dto;


public class UserDto {

	private int userId;

	private String userName;

	private String hashPassword;

	private int enabled;

	private int role;

	private String authKey;

	private int loginFailNum;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public int getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(int loginFailNum) {
		this.loginFailNum = loginFailNum;
	}

	public UserDto(int userId, String userName, String hashPassword, int enabled, int role, String authKey,
			int loginFailNum, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.hashPassword = hashPassword;
		this.enabled = enabled;
		this.role = role;
		this.authKey = authKey;
		this.loginFailNum = loginFailNum;
		this.email = email;
	}
	public UserDto() {
		super();
	}
}
