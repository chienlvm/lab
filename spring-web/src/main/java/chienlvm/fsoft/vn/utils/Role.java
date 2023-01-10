package chienlvm.fsoft.vn.utils;

/**
 * Role infor
 * @author chien
 *
 */
public enum Role {
	ADMIN("admin", 0),
	USER("user", 1);

	private String roleName;
    private int roleValue;
    
    private Role(String roleName, int roleValue) {
    	this.roleName = roleName;
    	this.roleValue = roleValue;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(int roleValue) {
		this.roleValue = roleValue;
	}
}
