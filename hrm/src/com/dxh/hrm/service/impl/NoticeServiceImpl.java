package com.dxh.hrm.service.impl;

import com.dxh.hrm.dao.NoticeDao;
import com.dxh.hrm.dao.impl.NoticeDaoImpl;
import com.dxh.hrm.entity.Notice;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	NoticeDao noticeDao = new NoticeDaoImpl();
	
	@Override
	public PageBean<Notice> findByPage(int pageNow) {
		return noticeDao.findByPage(pageNow);
	}

	@Override
	public boolean insert(Notice notice) {
		return noticeDao.insert(notice);
	}

	@Override
	public Notice findByOne(int id) {
		return noticeDao.findByOne(id);
	}

	@Override
	public boolean update(Notice notice) {
		return noticeDao.update(notice);
	}

	@Override
	public boolean delete(int id) {
		return noticeDao.delete(id);
	}

	@Override
	public PageBean<Notice> findBySome(int pageNow, Notice notice) {
		return noticeDao.findBySome(pageNow, notice);
	}

}
