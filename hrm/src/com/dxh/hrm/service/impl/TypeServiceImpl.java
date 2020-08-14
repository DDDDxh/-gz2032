package com.dxh.hrm.service.impl;

import java.util.List;

import com.dxh.hrm.dao.TypeDao;
import com.dxh.hrm.dao.impl.TypeDaoImpl;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.Type;
import com.dxh.hrm.service.TypeService;

public class TypeServiceImpl implements TypeService {
	
	TypeDao typeDao = new TypeDaoImpl(); 
	
	@Override
	public PageBean<Type> findByPage(int pageNow) {
		return typeDao.findByPage(pageNow);
	}

	@Override
	public Type findById(int id) {
		return typeDao.findById(id);
	}

	@Override
	public List<Type> findAll() {
		return typeDao.findAll();
	}

	@Override
	public boolean insert(Type type) {
		return typeDao.insert(type);
	}

	@Override
	public boolean update(Type type) {
		return typeDao.update(type);
	}

	@Override
	public PageBean<Type> findBySome(int pageNow, Type type) {
		return typeDao.findBySome(pageNow, type);
	}

	@Override
	public boolean delete(int id) {
		return typeDao.delete(id);
	}

}
