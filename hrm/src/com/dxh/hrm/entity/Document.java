package com.dxh.hrm.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class Document {
	private int id;
	private String title;
	private String filename;
	private String filetype;
	private byte[] filebytes;
	private String remark;
	private Timestamp createdate;
	private User user;

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(int id, String title, String filename, String filetype, byte[] filebytes, String remark,
			Timestamp createdate, User user) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.filebytes = filebytes;
		this.remark = remark;
		this.createdate = createdate;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public byte[] getFilebytes() {
		return filebytes;
	}

	public void setFilebytes(byte[] filebytes) {
		this.filebytes = filebytes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", filename=" + filename + ", filetype=" + filetype
				+ ", filebytes=" + Arrays.toString(filebytes) + ", remark=" + remark + ", createdate=" + createdate
				+ ", user=" + user + "]";
	}


	
	
}
