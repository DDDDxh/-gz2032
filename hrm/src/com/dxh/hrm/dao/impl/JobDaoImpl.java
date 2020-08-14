package com.dxh.hrm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dxh.hrm.dao.JobDao;
import com.dxh.hrm.entity.Job;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.util.AliUtil;

public class JobDaoImpl extends AliUtil<Job> implements JobDao {

	@Override
	public PageBean<Job> findByPage(int pageNow) {
		PageBean<Job> pb = new PageBean<Job>();
		pb.setPageNow(pageNow);
		String sql = "select count(*) from job_inf";
		pb.setRowCount(queryCount(sql));
		sql = "select * from job_inf limit ?,?";
		pb.setList(query(sql,  (pageNow-1)*pb.getPageSize(),pb.getPageSize()));
		return pb;
	}

	@Override
	public PageBean<Job> findBySome(int pageNow, Job job) {
		List<Object> obj = new ArrayList<>();
		PageBean<Job> pb = new PageBean<>();
		pb.setPageNow(pageNow);
		//查询总条数
		String sql = "select count(*) from job_inf where 1=1 ";
		if(job.getName()!=null) {
			sql = sql +"and name like ? ";
			obj.add("%"+job.getName()+"%");
		}
		pb.setRowCount(queryCount(sql, obj.toArray()));
		//查询内容
		sql = "select * from job_inf where 1=1 ";
		if(job.getName()!=null) {
			sql = sql +"and name like ? ";
		}
		sql = sql +"limit ?,? ";
		obj.add((pageNow-1)*pb.getPageSize());
		obj.add(pb.getPageSize());
		pb.setList(query(sql, obj.toArray()));
		return pb;
	}

	@Override
	public Job findByOne(int id) {
		String sql = "select * from job_inf where id = ?";
		List<Job> list = query(sql, id);
		if(list.size()>0) {
			return list.get(0); 
		}
		return null;
	}

	@Override
	public boolean insert(Job job) {
		String sql = "insert into job_inf values(null,?,?,0)";
		List<Object> list = new ArrayList<>();
		list.add(job.getName());
		list.add(job.getRemark());
		return update(sql, list);
	}

	@Override
	public boolean update(Job job) {
		String sql = "update job_inf set name = ? , remark = ? where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(job.getName());
		list.add(job.getRemark());
		list.add(job.getId());
		return update(sql, list);
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from job_inf where id = ?";
		List<Object> list = new ArrayList<>();
		list.add(id);
		return update(sql, list);
	}
	
	@Override
	public Job getEntity(ResultSet rs) throws Exception {
		Job job = new Job();
		job.setId(rs.getInt(1));
		job.setName(rs.getString(2));
		job.setRemark(rs.getString(3));
		job.setStatus(rs.getInt(4));
		return job;
	}

	@Override
	public List<Job> findAll() {
		String sql = "select * from job_inf";
		return query(sql);
	}
}
