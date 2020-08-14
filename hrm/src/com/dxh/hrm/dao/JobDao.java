package com.dxh.hrm.dao;

import java.util.List;

import com.dxh.hrm.entity.Job;
import com.dxh.hrm.entity.PageBean;

public interface JobDao {
	public PageBean<Job> findByPage(int pageNow);
	PageBean<Job> findBySome(int pageNow, Job job);
	public Job findByOne(int id);
	public boolean insert(Job job);
	public boolean update(Job job);
	public boolean delete(int id);
	public List<Job> findAll();
}
