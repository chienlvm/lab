package chienlvm.fsoft.vn.entity.els;

public class UserInfo {
	private Long userId;
	private String email;
	private String useName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", email=" + email + ", useName=" + useName + "]";
	}

	public UserInfo(Long userId, String email, String useName) {
		super();
		this.userId = userId;
		this.email = email;
		this.useName = useName;
	}

}
