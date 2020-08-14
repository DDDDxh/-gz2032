package com.dxh.hrm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxh.hrm.entity.Job;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.JobService;
import com.dxh.hrm.service.impl.JobServiceImpl;


@WebServlet({ "/jobServlet", "/joblist.action","/jobdel.action","/viewJob.action","/jobedit.action","/jobaddsave.action" })
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JobService jobService = new JobServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取到请求路径
		String uri = request.getRequestURI();
		// 截取请求名
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		int pageNow=1;
		if("joblist.action".equals(action)) {
			PageBean<Job> pb = null;
			String now = request.getParameter("pageIndex");
			String name = request.getParameter("jobname");
			if(now != null) {
				pageNow = Integer.parseInt(now);
			}
			if(name != null) {
				Job job = new Job();
				job.setName(name);
				request.setAttribute("job", job);
				pb = jobService.findBySome(pageNow,job);
			}else {
				pb = jobService.findByPage(pageNow);
			}
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/job/joblist.jsp").forward(request, response);
		}else if("jobdel.action".equals(action)) {
			boolean flag = false;
			String[] strs = request.getParameterValues("jobIds");
			for (String str : strs) {
				flag = jobService.delete(Integer.parseInt(str));
			}
			if(flag) {
				request.getRequestDispatcher("/joblist.action").forward(request, response);
			}
		}else if("viewJob.action".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Job job = jobService.findByOne(id);
			request.setAttribute("job", job);
			request.getRequestDispatcher("/WEB-INF/jsp/job/jobedit.jsp").forward(request, response);
		}else if("jobedit.action".equals(action)) {
			Job job = new Job();
			job.setId(Integer.parseInt(request.getParameter("id")));
			job.setName(request.getParameter("name"));
			job.setRemark(request.getParameter("remark"));
			boolean flag = jobService.update(job);
			if(flag) {
				request.getRequestDispatcher("/joblist.action").forward(request, response);
			}
		}else if("jobaddsave.action".equals(action)) {
			Job job = new Job();
			job.setName(request.getParameter("name"));
			job.setRemark(request.getParameter("remark"));
			boolean flag = jobService.insert(job);
			if(flag) {
				request.getRequestDispatcher("/joblist.action").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
