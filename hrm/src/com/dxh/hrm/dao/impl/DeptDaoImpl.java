package com.dxh.hrm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dxh.hrm.dao.DeptDao;
import com.dxh.hrm.entity.Department;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.util.AliUtil;

public class DeptDaoImpl extends AliUtil<Department> implements DeptDao {

	@Override
	public PageBean<Department> findByPage(int pageNow) {
		PageBean<Department> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from dept_inf";
		pb.setRowCount(queryCount(sql));
		sql = "select * from dept_inf limit ?,?";
		pb.setList(query(sql, (pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		return pb;
	}
	
	@Override
	public PageBean<Department> findBySome(int pageNow, Department dept) {
		List<Object> obj = new ArrayList<>();
		PageBean<Department> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		//查询总条数
		String sql = "select count(*) from dept_inf where 1=1 ";
		if(dept.getName()!=null) {
			sql = sql +"and name like ? ";
			obj.add("%"+dept.getName()+"%");
		}
		pb.setRowCount(queryCount(sql, obj.toArray()));
		//查询内容
		sql = "select * from dept_inf where 1=1 ";
		if(dept.getName()!=null) {
			sql = sql +"and name like ? ";
		}
		sql = sql +"limit ?,? ";
		obj.add((pageNow-1)*pb.getPageSize());
		obj.add(pb.getPageSize());
		pb.setList(query(sql, obj.toArray()));
		return pb;
	}

	@Override
	public Department findByOne(int id) {
		String sql = "select * from dept_inf where id = ?";
		List<Department> list = query(sql, id);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public boolean insert(Department dept) {
		String sql = "insert into dept_inf values(null,?,?,null)";
		List<Object> list = new ArrayList<>();
		list.add(dept.getName());
		list.add(dept.getRemark());
		return update(sql, list);
	}
	
	@Override
	public boolean update(Department dept) {
		String sql = "update dept_inf set name = ? , remark = ? where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(dept.getName());
		list.add(dept.getRemark());
		list.add(dept.getId());
		return update(sql, list);
	}
	
	@Override
	public boolean delete(int id) {
		String sql = "delete from dept_inf where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(id);
		return update(sql, list);
	}
	
	@Override
	public Department getEntity(ResultSet rs) throws Exception {
		Department dept = new Department();
		dept.setId(rs.getInt(1));
		dept.setName(rs.getString(2));
		dept.setRemark(rs.getString(3));
		dept.setStatus(rs.getInt(4));
		return dept;
	}

	@Override
	public List<Department> findAll() {
		String sql = "select * from dept_inf";
		List<Department> list = query(sql);
		if(list.size()>0) {
			return list;
		}
		return null;
	}
}
