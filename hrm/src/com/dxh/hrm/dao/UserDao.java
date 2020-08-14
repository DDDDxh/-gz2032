package com.dxh.hrm.dao;

import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.User;

public interface UserDao {
	public User findByOne(User user);
	public boolean insert(User user);
	public PageBean<User> findByPage(int pageNow);
	public PageBean<User> findBySome(int pageNow,User user);
	public boolean delete(int id);
	public User findByOne(int id);
	public boolean update(User user);
}
