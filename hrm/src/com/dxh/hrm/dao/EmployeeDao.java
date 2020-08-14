package com.dxh.hrm.dao;


import com.dxh.hrm.entity.Employee;
import com.dxh.hrm.entity.PageBean;

public interface EmployeeDao {
	public PageBean<Employee> findByPage(int pageNow);
	public boolean insert(Employee emp);
	public boolean findByCardId(String cardId);
	public PageBean<Employee> findBySome(int pageNow,Employee emp);
	public boolean delete(int id);
	public Employee findByOne(int id);
	public boolean update(Employee emp);
}
