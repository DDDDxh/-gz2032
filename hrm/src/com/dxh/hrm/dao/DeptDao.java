package com.dxh.hrm.dao;

import java.util.List;

import com.dxh.hrm.entity.Department;
import com.dxh.hrm.entity.PageBean;

public interface DeptDao {
	public PageBean<Department> findByPage(int pageNow);
	public PageBean<Department> findBySome(int pageNow,Department dept);
	public boolean insert(Department dept);
	public Department findByOne(int id);
	public boolean update(Department dept);
	public boolean delete(int id);
	public List<Department> findAll();
}
