package com.dxh.hrm.dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dxh.hrm.dao.DeptDao;
import com.dxh.hrm.dao.EmployeeDao;
import com.dxh.hrm.dao.JobDao;
import com.dxh.hrm.entity.Employee;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.util.AliUtil;

public class EmployeeDaoImpl extends AliUtil<Employee> implements EmployeeDao {
	DeptDao deptDao = new DeptDaoImpl();
	JobDao jobDao = new JobDaoImpl(); 

	@Override
	public PageBean<Employee> findByPage(int pageNow) {
		PageBean<Employee> pb = new PageBean<Employee>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from employee_inf";
		pb.setRowCount(queryCount(sql));
		sql = "select * from employee_inf limit ?,? ";
		pb.setList(query(sql,  (pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		return pb;
	}
	
	@Override
	public Employee getEntity(ResultSet rs) throws Exception {
		Employee emp = new Employee();
		emp.setId(rs.getInt(1));
		emp.setName(rs.getString(2));
		emp.setCardId(rs.getString(3));
		emp.setAddress(rs.getString(4));
		emp.setPostCode(rs.getString(5));
		emp.setTel(rs.getString(6));
		emp.setPhone(rs.getString(7));
		emp.setQqNum(rs.getString(8));
		emp.setEmail(rs.getString(9));
		emp.setSex(rs.getInt(10));
		emp.setParty(rs.getString(11));
		emp.setBrithday(rs.getTimestamp(12));
		emp.setRace(rs.getString(13));
		emp.setEducation(rs.getString(14));
		emp.setSpeciality(rs.getString(15));
		emp.setHobby(rs.getString(16));
		emp.setRemark(rs.getString(17));
		emp.setCreateDate(rs.getTimestamp(18));
		emp.setStatus(rs.getInt(19));
		emp.setDept(deptDao.findByOne(rs.getInt(20)));
		emp.setJob(jobDao.findByOne(rs.getInt(21)));
		return emp;
	}

	@Override
	public boolean insert(Employee emp) {
		String sql = "insert into employee_inf values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<>();
		list.add(emp.getName());
		list.add(emp.getCardId());
		list.add(emp.getAddress());
		list.add(emp.getPostCode());
		list.add(emp.getTel());
		list.add(emp.getPhone());
		list.add(emp.getQqNum());
		list.add(emp.getEmail());
		list.add(emp.getSex());
		list.add(emp.getParty());
		list.add(emp.getBrithday());
		list.add(emp.getRace());
		list.add(emp.getEducation());
		list.add(emp.getSpeciality());
		list.add(emp.getHobby());
		list.add(emp.getRemark());
		list.add(new Timestamp(new Date().getTime()));
		list.add(0);
		list.add(emp.getDept().getId());
		list.add(emp.getJob().getId());
		return update(sql, list);
	}

	@Override
	public boolean findByCardId(String cardId) {
		String sql = "select * from employee_inf where card_id = ?";
		List<Employee> list = query(sql, cardId);
		if(list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public PageBean<Employee> findBySome(int pageNow, Employee emp) {
		List<Object> obj = new ArrayList<>();
		PageBean<Employee> pb = new PageBean<Employee>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from employee_inf where 1=1 ";
		if(emp.getJob() != null) {
			sql = sql + "and job_id = ? ";
			obj.add(emp.getJob().getId());
		}
		if(emp.getName() != "") {
			sql = sql + "and name = ? ";
			obj.add(emp.getName());
		}
		if(emp.getCardId() != "") {
			sql = sql +"and card_id = ? ";
			obj.add(emp.getCardId());
		}
		if(emp.getSex() == 1 || emp.getSex() == 2) {
			sql = sql + "and sex = ? ";
			obj.add(emp.getSex());
		}
		if(emp.getPhone() != "") {
			sql = sql +"and phone = ? ";
			obj.add(emp.getPhone());
		}
		if(emp.getDept() != null) {
			sql = sql + "and dept_id = ? ";
			obj.add(emp.getDept().getId());
		}
		pb.setRowCount(queryCount(sql,obj.toArray()));
		sql = "select * from employee_inf where 1=1 ";
		if(emp.getJob() != null) {
			sql = sql + "and job_id = ? ";
		}
		if(emp.getName() != "") {
			sql = sql + "and name = ? ";
		}
		if(emp.getCardId() != "") {
			sql = sql +"and card_id = ? ";
		}
		if(emp.getSex() == 1 || emp.getSex() == 2) {
			sql = sql + "and sex = ? ";
		}
		if(emp.getPhone() != "") {
			sql = sql +"and phone = ? ";
		}
		if(emp.getDept() != null) {
			sql = sql + "and dept_id = ? ";
		}
		sql = sql +"limit ?,? ";
		obj.add((pageNow-1)*pb.getPageSize());
		obj.add(pb.getPageSize());
		pb.setList(query(sql, obj.toArray()));
		return pb;
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from employee_inf where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(id);
		return update(sql, list);
	}

	@Override
	public Employee findByOne(int id) {
		String sql = "select * from employee_inf where id = ?";
		List<Employee> list = query(sql, id);
		if(list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean update(Employee emp) {
		String sql = "update employee_inf set name = ? , card_id = ? , address = ? , post_code = ? ,tel = ? ,phone = ? , qq_num = ? , "
				+ "email = ? , sex = ? , party = ?, birthday = ? ,race = ? , education = ? , speciality = ? , hobby = ? , remark = ? , dept_id = ? , job_id = ? "
				+ "where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(emp.getName());
		list.add(emp.getCardId());
		list.add(emp.getAddress());
		list.add(emp.getPostCode());
		list.add(emp.getTel());
		list.add(emp.getPhone());
		list.add(emp.getQqNum());
		list.add(emp.getEmail());
		list.add(emp.getSex());
		list.add(emp.getParty());
		list.add(emp.getBrithday());
		list.add(emp.getRace());
		list.add(emp.getEducation());
		list.add(emp.getSpeciality());
		list.add(emp.getHobby());
		list.add(emp.getRemark());
		list.add(emp.getDept().getId());
		list.add(emp.getJob().getId());
		list.add(emp.getId());
		return update(sql, list);
	}


}
