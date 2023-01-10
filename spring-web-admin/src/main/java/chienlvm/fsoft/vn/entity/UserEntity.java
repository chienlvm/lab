package chienlvm.fsoft.vn.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_login")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@JsonIgnore
	@Column(name = "HASH_PASSWORD", nullable = false)
	private String hashPassword;

	@Column(name = "ENABLED")
	private int enabled;

	@Column(name = "ROLE")
	private int role;

	@Column(name = "AUTH_KEY")
	private String authKey;

	@JsonIgnore
	@Column(name = "LOGIN_FAIL_NUM")
	private int loginFailNum;

	@Column(name = "EMAIL")
	private String email;

	public UserEntity() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	@Transient
	List<GrantedAuthority> grantList;

	public List<GrantedAuthority> getGrantList() {
		return grantList;
	}

	public void setGrantList(List<GrantedAuthority> grantList) {
		this.grantList = grantList;
	}

	public UserEntity(String userName, String hashPassword, List<GrantedAuthority> grantList) {
		this.userName = userName;
		this.hashPassword = hashPassword;
		this.grantList = grantList;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

}
