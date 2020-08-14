package com.dxh.hrm.service.impl;


import com.dxh.hrm.dao.EmployeeDao;
import com.dxh.hrm.dao.impl.EmployeeDaoImpl;
import com.dxh.hrm.entity.Employee;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.EmpService;

public class EmpServiceImpl implements EmpService {

	EmployeeDao empDao = new EmployeeDaoImpl();
	
	@Override
	public PageBean<Employee> findByPage(int pageNow) {
		return empDao.findByPage(pageNow);
	}

	@Override
	public boolean insert(Employee emp) {
		return empDao.insert(emp);
	}

	@Override
	public boolean findByCardId(String cardId) {
		return empDao.findByCardId(cardId);
	}

	@Override
	public PageBean<Employee> findBySome(int pageNow, Employee emp) {
		return empDao.findBySome(pageNow, emp);
	}

	@Override
	public boolean delete(int id) {
		return empDao.delete(id);
	}

	@Override
	public Employee findByOne(int id) {
		return empDao.findByOne(id);
	}

	@Override
	public boolean update(Employee emp) {
		return empDao.update(emp);
	}

}
