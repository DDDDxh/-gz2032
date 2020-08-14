package com.dxh.hrm.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String name;
	private Date createDate;
	private Type type;
	private String content;
	private User user;
	private Date modify_date;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int id, String name, Date createDate, Type type, String content, User user, Date modify_date) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.type = type;
		this.content = content;
		this.user = user;
		this.modify_date = modify_date;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", name=" + name + ", createDate=" + createDate + ", type=" + type + ", content="
				+ content + ", user=" + user + ", modify_date=" + modify_date + "]";
	}
	
	
}
