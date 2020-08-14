package com.dxh.hrm.service.impl;

import com.dxh.hrm.dao.DocDao;
import com.dxh.hrm.dao.impl.DocDaoImpl;
import com.dxh.hrm.entity.Document;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.DocService;

public class DocServiceImpl implements DocService {
	DocDao docDao = new DocDaoImpl();
	@Override
	public boolean insert(Document doc) {
		return docDao.insert(doc);
	}
	@Override
	public PageBean<Document> findByPage(int pageNow) {
		return docDao.findByPage(pageNow);
	}
	@Override
	public PageBean<Document> findBySome(int pageNow, Document doc) {
		return docDao.findBySome(pageNow, doc);
	}
	@Override
	public boolean delete(int id) {
		return docDao.delete(id);
	}
	@Override
	public Document findByOne(int id) {
		return docDao.findByOne(id);
	}
	@Override
	public boolean update(Document doc) {
		return docDao.update(doc);
	}

}
