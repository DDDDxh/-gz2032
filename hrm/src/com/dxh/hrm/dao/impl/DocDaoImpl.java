package com.dxh.hrm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dxh.hrm.dao.DocDao;
import com.dxh.hrm.dao.UserDao;
import com.dxh.hrm.entity.Document;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.util.AliUtil;

public class DocDaoImpl extends AliUtil<Document> implements DocDao {
	UserDao userDao = new UserDaoImpl();

	@Override
	public boolean insert(Document doc) {
		String sql = "insert into document_inf values(null,?,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<>();
		list.add(doc.getTitle());
		list.add(doc.getFilename());
		list.add(doc.getFiletype());
		list.add(doc.getFilebytes());
		list.add(doc.getRemark());
		list.add(doc.getCreatedate());
		list.add(doc.getUser().getId());
		return update(sql, list);
	}

	@Override
	public Document getEntity(ResultSet rs) throws Exception {
		Document doc = new Document();
		doc.setId(rs.getInt(1));
		doc.setTitle(rs.getString(2));
		doc.setFilename(rs.getString(3));
		doc.setFiletype(rs.getString(4));
		doc.setFilebytes(rs.getBytes(5));
		doc.setRemark(rs.getString(6));
		doc.setCreatedate(rs.getTimestamp(7));
		doc.setUser(userDao.findByOne(rs.getInt(8)));
		return doc;
	}

	@Override
	public PageBean<Document> findByPage(int pageNow) {
		PageBean<Document> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from document_inf";
		pb.setRowCount(queryCount(sql));
		sql = "select * from document_inf limit ?,?";
		pb.setList(query(sql, (pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		return pb;
	}

	@Override
	public PageBean<Document> findBySome(int pageNow, Document doc) {
		List<Object> obj = new ArrayList<>();
		PageBean<Document> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		//查询总条数
		String sql = "select count(*) from document_inf where 1=1 ";
		if(doc.getTitle()!=null) {
			sql = sql +"and title like ? ";
			obj.add("%"+doc.getTitle()+"%");
		}
		pb.setRowCount(queryCount(sql, obj.toArray()));
		//查询内容
		sql = "select * from document_inf where 1=1 ";
		if(doc.getTitle()!=null) {
			sql = sql +"and title like ? ";
		}
		sql = sql +"limit ?,? ";
		obj.add((pageNow-1)*pb.getPageSize());
		obj.add(pb.getPageSize());
		pb.setList(query(sql, obj.toArray()));
		return pb;
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from document_inf where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(id);
		return update(sql, list);
	}

	@Override
	public Document findByOne(int id) {
		String sql = "select * from document_inf where id = ?";
		List<Document> list = query(sql, id);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean update(Document doc) {
		String sql = "update document_inf set title = ? , filename = ? , filetype=? , remark = ? , filebytes = ? , user_id = ? where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(doc.getTitle());
		list.add(doc.getFilename());
		list.add(doc.getFiletype());
		list.add(doc.getRemark());
		list.add(doc.getFilebytes());
		list.add(doc.getUser().getId());
		list.add(doc.getId());
		return update(sql, list);
	}

}
