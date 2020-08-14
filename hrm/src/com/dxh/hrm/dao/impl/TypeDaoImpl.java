package com.dxh.hrm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dxh.hrm.dao.TypeDao;
import com.dxh.hrm.dao.UserDao;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.Type;
import com.dxh.hrm.util.AliUtil;

public class TypeDaoImpl extends AliUtil<Type> implements TypeDao {
	UserDao userDao = new UserDaoImpl();

	@Override
	public PageBean<Type> findByPage(int pageNow) {
		PageBean<Type> pb = new PageBean<Type>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from type_inf";
		pb.setRowCount(queryCount(sql));
		sql = "select * from type_inf limit ?,?";
		pb.setList(query(sql,  (pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		return pb;
	}

	@Override
	public Type getEntity(ResultSet rs) throws Exception {
		Type type = new Type();
		type.setId(rs.getInt(1));
		type.setName(rs.getString(2));
		type.setCreateDate(rs.getDate(3));
		type.setStatus(rs.getInt(4));
		type.setUser(userDao.findByOne(rs.getInt(5)));
		type.setModifyDate(rs.getDate(6));
		return type;
	}

	@Override
	public Type findById(int id) {
		String sql ="select * from type_inf where id = ?";
		List<Type> list = query(sql, id);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Type> findAll() {
		String sql = "select * from type_inf";
		List<Type> list = query(sql);
		if(list.size() > 0 ) {
			return list;
		}
		return null;
	}

	@Override
	public boolean insert(Type type) {
		String sql = "insert into type_inf values(null,?,?,?,?,?)";
		List<Object> list = new ArrayList<>();
		list.add(type.getName());
		list.add(type.getCreateDate());
		list.add(type.getStatus());
		list.add(type.getUser().getId());
		list.add(type.getModifyDate());
		return update(sql, list);
	}

	@Override
	public boolean update(Type type) {
		String sql = "update type_inf set name = ? , user_id = ? , modify_date = ? where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(type.getName());
		list.add(type.getUser().getId());
		list.add(type.getModifyDate());
		list.add(type.getId());
		return update(sql,list);
	}

	@Override
	public PageBean<Type> findBySome(int pageNow, Type type) {
		PageBean<Type> pb = new PageBean<Type>();
		List<Object> list = new ArrayList<>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from type_inf where 1=1 ";
		if(type.getName() != null) {
			sql = sql + "and name like ? ";
			list.add("%"+type.getName()+"%");
		}
		pb.setRowCount(queryCount(sql,list.toArray()));
		sql = "select * from type_inf where 1=1 ";
		if(type.getName() != null) {
			sql = sql +"and name like ? ";
		}
		sql = sql +"limit ?,? ";
		list.add((pageNow-1)*pb.getPageSize());
		list.add(pb.getPageSize());
		pb.setList(query(sql, list.toArray()));
		return pb;
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from type_inf where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(id);
		return update(sql, list);
	}

}
