package com.dxh.hrm.dao;

import com.dxh.hrm.entity.Notice;
import com.dxh.hrm.entity.PageBean;

public interface NoticeDao {
	public PageBean<Notice> findByPage(int pageNow);
	public PageBean<Notice> findBySome(int pageNow,Notice notice);
	boolean insert(Notice notice);
	Notice findByOne(int id);
	boolean update(Notice notice);
	boolean delete(int id);
}
