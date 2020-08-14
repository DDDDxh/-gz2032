package com.dxh.hrm.entity;

public class Department {
	private int id;
	private String name;
	private String remark;
	private int status;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int id, String name, String remark, int status) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.status = status;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", remark=" + remark + ", status=" + status + "]";
	}
	
	
}
