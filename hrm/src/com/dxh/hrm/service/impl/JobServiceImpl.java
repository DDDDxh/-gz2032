package com.dxh.hrm.service.impl;

import java.util.List;

import com.dxh.hrm.dao.JobDao;
import com.dxh.hrm.dao.impl.JobDaoImpl;
import com.dxh.hrm.entity.Job;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.JobService;

public class JobServiceImpl implements JobService {

	JobDao jobDao = new JobDaoImpl();
	
	@Override
	public PageBean<Job> findByPage(int pageNow) {
		return jobDao.findByPage(pageNow);
	}

	@Override
	public PageBean<Job> findBySome(int pageNow, Job job) {
		return jobDao.findBySome(pageNow, job);
	}

	@Override
	public Job findByOne(int id) {
		return jobDao.findByOne(id);
	}

	@Override
	public boolean insert(Job job) {
		return jobDao.insert(job);
	}

	@Override
	public boolean update(Job job) {
		return jobDao.update(job);
	}

	@Override
	public boolean delete(int id) {
		return jobDao.delete(id);
	}

	@Override
	public List<Job> findAll() {
		return jobDao.findAll();
	}

}
