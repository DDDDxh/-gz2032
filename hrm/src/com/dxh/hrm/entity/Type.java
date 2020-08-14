package com.dxh.hrm.entity;

import java.util.Date;

public class Type {
	private int id;
	private String name;
	private Date createDate;
	private int status;
	private User user;
	private Date modifyDate;
	
	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Type(int id, String name, Date createDate, int status, User user, Date modifyDate) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.status = status;
		this.user = user;
		this.modifyDate = modifyDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", createDate=" + createDate + ", status=" + status + ", user="
				+ user + ", modifyDate=" + modifyDate + "]";
	}
	
	
}
