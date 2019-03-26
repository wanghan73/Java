package com.hp.po;

import java.util.Date;

public class Users {
	/*成员变量：
	构造方法：无参、带参
	成员方法：getXxx、setXxx、tostring*/
	private int userId;
	private String userName;
	private String userPwd;
	private String realName;
	private String tel;
	private String address;
	private Date loginTime;
	
	public Users(){}
	
	public Users(int userId, String userName, String userPwd, String realName, String tel, String address,
			Date loginTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.realName = realName;
		this.tel = tel;
		this.address = address;
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", realName=" + realName
				+ ", tel=" + tel + ", address=" + address + ", loginTime=" + loginTime + "]";
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

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
}
