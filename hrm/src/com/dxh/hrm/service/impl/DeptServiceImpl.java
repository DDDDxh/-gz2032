package com.dxh.hrm.service.impl;

import java.util.List;

import com.dxh.hrm.dao.DeptDao;
import com.dxh.hrm.dao.impl.DeptDaoImpl;
import com.dxh.hrm.entity.Department;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.DeptService;

public class DeptServiceImpl implements DeptService {

	DeptDao deptDao = new DeptDaoImpl();
	
	@Override
	public PageBean<Department> findByPage(int pageNow) {
		return deptDao.findByPage(pageNow);
	}

	@Override
	public PageBean<Department> findBySome(int pageNow, Department dept) {
		return deptDao.findBySome(pageNow, dept);
	}

	@Override
	public boolean insert(Department dept) {
		return deptDao.insert(dept);
	}

	@Override
	public Department findByOne(int id) {
		return deptDao.findByOne(id);
	}

	@Override
	public boolean update(Department dept) {
		return deptDao.update(dept);
	}

	@Override
	public boolean delete(int id) {
		return deptDao.delete(id);
	}

	@Override
	public List<Department> findAll() {
		return deptDao.findAll();
	}

}
