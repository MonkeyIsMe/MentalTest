package CSU.Mental.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_user")
public class User implements Serializable{

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;  // 主键
	
	@Column(name="user_name")
	private String UserName;  // 用户名字
	
	@Column(name="user_account")
	private String UserAccount;  // 账号
	
	@Column(name="user_password")
	private String UserPassword;  // 密码

	@Column(name="user_role")
	private String UserRole;  // 职称

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	
	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("UserId", this.UserId);
		jo.put("UserPassword", this.UserPassword);
		jo.put("UserAccount", this.UserAccount);
		jo.put("UserName", this.UserName);
		jo.put("UserRole", this.UserRole);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
