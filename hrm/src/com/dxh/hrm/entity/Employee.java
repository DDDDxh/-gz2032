package com.dxh.hrm.entity;

import java.sql.Timestamp;

public class Employee {
	private int id;
	private String name;
	private String cardId;
	private String address;
	private String postCode;
	private String tel;
	private String phone;
	private String qqNum;
	private String email;
	private int sex;
	private String party;
	private Timestamp brithday;
	private String race;
	private String education;
	private String speciality;
	private String hobby;
	private String remark;
	private Timestamp createDate;
	private int status;
	private Department dept;
	private Job job;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, String cardId, String address, String postCode, String tel, String phone,
			String qqNum, String email, int sex, String party, Timestamp brithday, String race, String education,
			String speciality, String hobby, String remark, Timestamp createDate, int status, Department dept,
			Job job) {
		super();
		this.id = id;
		this.name = name;
		this.cardId = cardId;
		this.address = address;
		this.postCode = postCode;
		this.tel = tel;
		this.phone = phone;
		this.qqNum = qqNum;
		this.email = email;
		this.sex = sex;
		this.party = party;
		this.brithday = brithday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.createDate = createDate;
		this.status = status;
		this.dept = dept;
		this.job = job;
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

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Timestamp getBrithday() {
		return brithday;
	}

	public void setBrithday(Timestamp brithday) {
		this.brithday = brithday;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", cardId=" + cardId + ", address=" + address + ", postCode="
				+ postCode + ", tel=" + tel + ", phone=" + phone + ", qqNum=" + qqNum + ", email=" + email + ", sex="
				+ sex + ", party=" + party + ", brithday=" + brithday + ", race=" + race + ", education=" + education
				+ ", speciality=" + speciality + ", hobby=" + hobby + ", remark=" + remark + ", createDate="
				+ createDate + ", status=" + status + ", dept=" + dept + ", job=" + job + "]";
	}
	
	
}
