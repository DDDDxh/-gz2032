package com.dxh.hrm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dxh.hrm.dao.NoticeDao;
import com.dxh.hrm.dao.TypeDao;
import com.dxh.hrm.dao.UserDao;
import com.dxh.hrm.entity.Notice;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.util.AliUtil;

public class NoticeDaoImpl extends AliUtil<Notice> implements NoticeDao {
	TypeDao typeDao = new TypeDaoImpl();
	UserDao userDao = new UserDaoImpl();

	@Override
	public PageBean<Notice> findByPage(int pageNow) {
		PageBean<Notice> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from notice_inf";
		pb.setRowCount(queryCount(sql));
		sql = "select * from notice_inf limit ?,?";
		pb.setList(query(sql,  (pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		return pb;
	}

	@Override
	public Notice getEntity(ResultSet rs) throws Exception {
		Notice notice = new Notice();
		notice.setId(rs.getInt(1));
		notice.setName(rs.getString(2));
		notice.setCreateDate(rs.getDate(3));
		notice.setType(typeDao.findById(rs.getInt(4)));
		notice.setContent(rs.getString(5));
		notice.setUser(userDao.findByOne(rs.getInt(6)));
		notice.setModify_date(rs.getDate(7));
		return notice;
	}

	@Override
	public boolean insert(Notice notice) {
		String sql = "insert into notice_inf values(null,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<>();
		list.add(notice.getName());
		list.add(notice.getCreateDate());
		list.add(notice.getType().getId());
		list.add(notice.getContent());
		list.add(notice.getUser().getId());
		list.add(notice.getModify_date());
		return update(sql, list);
	}

	@Override
	public Notice findByOne(int id) {
		String sql = "select * from notice_inf where id = ? ";
		List<Notice> list = query(sql, id);
		if(list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean update(Notice notice) {
		String sql = "update notice_inf set name = ? , type_id = ? , content = ? , user_id = ? , modify_date = ? where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(notice.getName());
		list.add(notice.getType().getId());
		list.add(notice.getContent());
		list.add(notice.getUser().getId());
		list.add(notice.getModify_date());
		list.add(notice.getId());
		return update(sql, list);
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from notice_inf where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(id);
		return update(sql, list);
	}

	@Override
	public PageBean<Notice> findBySome(int pageNow, Notice notice) {
		PageBean<Notice> pb = new PageBean<Notice>();
		List<Object> list = new ArrayList<>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from notice_inf where 1=1 ";
		if(notice.getName() != null) {
			sql = sql + "and name like ? ";
			list.add("%"+notice.getName()+"%");
		}
		pb.setRowCount(queryCount(sql,list.toArray()));
		sql = "select * from notice_inf where 1=1 ";
		if(notice.getName() != null) {
			sql = sql +"and name like ? ";
		}
		sql = sql +"limit ?,? ";
		list.add((pageNow-1)*pb.getPageSize());
		list.add(pb.getPageSize());
		pb.setList(query(sql, list.toArray()));
		return pb;
	}

}
