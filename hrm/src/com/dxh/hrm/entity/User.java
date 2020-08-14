package com.dxh.hrm.entity;

import java.sql.Timestamp;

public class User {
	private int id;
	private String loginName;
	private String password;
	private int status;
	private Timestamp createDate;
	private String username;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int id, String loginName, String password, int status, Timestamp createDate, String username) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.password = password;
		this.status = status;
		this.createDate = createDate;
		this.username = username;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", status=" + status
				+ ", createDate=" + createDate + ", userName=" + username + "]";
	}
	
	
}
