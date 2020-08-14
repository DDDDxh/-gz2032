package com.dxh.hrm.dao;

import java.util.List;

import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.Type;

public interface TypeDao {
	public PageBean<Type> findByPage(int pageNow);
	public PageBean<Type> findBySome(int pageNow,Type type);
	public Type findById(int id);
	List<Type> findAll();
	boolean insert(Type type);
	boolean update(Type type);
	boolean delete(int id);
}
