package com.dxh.hrm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxh.hrm.entity.Department;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.DeptService;
import com.dxh.hrm.service.impl.DeptServiceImpl;

@WebServlet({ "/DepartmentServlet","/deptlist.action","/saveupdate.action","/viewDept.action","/deptdel.action"})
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DeptService deptService = new DeptServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		int pageNow = 1;
		if("deptlist.action".equals(action)) {
			PageBean<Department> pb = null;
			String now = request.getParameter("pageIndex");
			System.out.println(now);
			String deptname = request.getParameter("deptname");
			System.out.println(deptname);
			if(now != null) {
				pageNow = Integer.parseInt(now);
			}
			if(deptname != null) {
				Department dept = new Department();
				dept.setName(deptname);
				request.setAttribute("dept", dept);
				pb = deptService.findBySome(pageNow, dept);
			}else {
				pb = deptService.findByPage(pageNow);
			}
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/dept/deptlist.jsp").forward(request, response);
		}else if("saveupdate.action".equals(action)) {
			System.out.println("进来了");
			String id = request.getParameter("deptid");
			System.out.println(id);
			if(id == "") {
				System.out.println("进入了");
				Department dept = new Department();
				dept.setName(request.getParameter("name"));
				dept.setRemark(request.getParameter("remark"));
				boolean flag = deptService.insert(dept);
				if(flag) {
					request.getRequestDispatcher("/deptlist.action").forward(request, response);
				}
			}else if(id != "") {
				Department dept = new Department();
				dept.setId(Integer.parseInt(id));
				dept.setName(request.getParameter("name"));
				dept.setRemark(request.getParameter("remark"));
				boolean flag = deptService.update(dept);
				if(flag) {
					request.getRequestDispatcher("/deptlist.action").forward(request, response);
				}
			}
		}else if("viewDept.action".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Department dept = deptService.findByOne(id);
			request.setAttribute("dept", dept);
			request.getRequestDispatcher("/WEB-INF/jsp/dept/deptedit.jsp").forward(request, response);
		}else if("deptdel.action".equals(action)) {
			boolean flag = false;
			String[] strs = request.getParameterValues("deptIds");
			for (String str : strs) {
				flag = deptService.delete(Integer.parseInt(str));
			}
			if(flag) {
				request.getRequestDispatcher("/deptlist.action").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
