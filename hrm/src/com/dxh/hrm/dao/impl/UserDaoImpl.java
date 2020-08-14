package com.dxh.hrm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dxh.hrm.dao.UserDao;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.User;
import com.dxh.hrm.util.AliUtil;

public class UserDaoImpl extends AliUtil<User> implements UserDao {

	@Override
	public User findByOne(User user) {
		String sql = "select * from user_inf where loginname = ? and password = ?";
		List<User> list = query(sql, user.getLoginName(),user.getPassword());
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public User findByOne(int id) {
		String sql = "select * from user_inf where id = ?";
		List<User> list = query(sql, id);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public boolean insert(User user) {
		String sql = "insert into user_inf values(null,?,?,?,?,?)";
		List<Object> list = new ArrayList<>();
		list.add(user.getLoginName());
		list.add(user.getPassword());
		list.add(user.getStatus());
		list.add(user.getCreateDate());
		list.add(user.getUsername());
		return update(sql, list);
	}
	
	@Override
	public PageBean<User> findByPage(int pageNow) {
		PageBean<User> pb = new PageBean<User>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from user_inf";
		pb.setRowCount(queryCount(sql));
		sql = "select * from user_inf limit ?,?";
		pb.setList(query(sql,  (pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		return pb;
	}
	
	@Override
	public PageBean<User> findBySome(int pageNow, User user) {
		List<Object> obj = new ArrayList<>();
		PageBean<User> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		//查询总条数
		String sql = "select count(*) from user_inf where 1=1 ";
		if(user.getLoginName()!=null) {
			sql = sql +"and loginname like ? ";
			obj.add("%"+user.getLoginName()+"%");
		}
		if(user.getUsername()!=null) {
			sql = sql +"and username like ? ";
			obj.add("%"+user.getUsername()+"%");
		}
		if(user.getStatus() > 0) {
			sql = sql +"and status = ? ";
			obj.add(user.getStatus());
		}
		pb.setRowCount(queryCount(sql, obj.toArray()));
		//查询内容
		sql = "select * from user_inf where 1=1 ";
		if(user.getLoginName()!=null) {
			sql = sql +"and loginname like ? ";
		}
		if(user.getUsername()!=null) {
			sql = sql +"and username like ? ";
		}
		if(user.getStatus() > 0) {
			sql = sql +"and status = ? ";
		}
		sql = sql +"limit ?,? ";
		obj.add((pageNow-1)*pb.getPageSize());
		obj.add(pb.getPageSize());
		pb.setList(query(sql, obj.toArray()));
		return pb;
	}
	
	@Override
	public boolean delete(int id) {
		String sql = "delete from user_inf where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(id);
		return update(sql, list);
	}
	
	@Override
	public boolean update(User user) {
		String sql = "update user_inf set loginname = ? , password = ? ,status = ? , username = ? where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(user.getLoginName());
		list.add(user.getPassword());
		list.add(user.getStatus());
		list.add(user.getUsername());
		list.add(user.getId());
		return update(sql, list);
	}

	@Override
	public User getEntity(ResultSet rs) throws Exception {
		User user = new User();
		user.setId(rs.getInt(1));
		user.setLoginName(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setStatus(rs.getInt(4));
		user.setCreateDate(rs.getTimestamp(5));
		user.setUsername(rs.getString(6));
		return user;
	}

}
