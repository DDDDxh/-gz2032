package com.dxh.hrm.service.impl;


import com.dxh.hrm.dao.UserDao;
import com.dxh.hrm.dao.impl.UserDaoImpl;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.User;
import com.dxh.hrm.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	
	@Override
	public User findByOne(User user) {
		return userDao.findByOne(user);
	}

	@Override
	public boolean insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public PageBean<User> findByPage(int pageNow) {
		return userDao.findByPage(pageNow);
	}

	@Override
	public PageBean<User> findBySome(int pageNow, User user) {
		return userDao.findBySome(pageNow, user);
	}

	@Override
	public boolean delete(int id) {
		return userDao.delete(id);
	}

	@Override
	public User findByOne(int id) {
		return userDao.findByOne(id);
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}


}
