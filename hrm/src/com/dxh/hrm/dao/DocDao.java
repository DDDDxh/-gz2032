package com.dxh.hrm.dao;

import com.dxh.hrm.entity.Document;
import com.dxh.hrm.entity.PageBean;

public interface DocDao {
	public boolean insert(Document doc);
	public PageBean<Document> findByPage(int pageNow);
	public PageBean<Document> findBySome(int pageNow,Document doc);
	public boolean delete(int id);
	public Document findByOne(int id);
	public boolean update(Document doc);
}
