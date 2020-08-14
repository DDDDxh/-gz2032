package com.dxh.hrm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.User;
import com.dxh.hrm.service.UserService;
import com.dxh.hrm.service.impl.UserServiceImpl;


@WebServlet({  "/useraddsave.action","/userlist.action","/queryUser.action","/userdel.action","/viewUser.action","/useredit.action" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNow = 1;
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("useraddsave.action".equals(action)) {
			User user = new User(); 
			user.setLoginName(request.getParameter("loginname"));
			user.setPassword(request.getParameter("password"));
			user.setStatus(Integer.parseInt(request.getParameter("status")));
			user.setCreateDate(new Timestamp(new Date().getTime()));
			user.setUsername(request.getParameter("username"));
			boolean flag = userService.insert(user);
			if(flag) {
				response.sendRedirect(request.getServletContext().getContextPath()+"/userlist.action");
			}
		}else if("userlist.action".equals(action)) {
			PageBean<User> pb = null;
			String now = request.getParameter("pageIndex");
			String loginName = request.getParameter("loginname");
			String userName = request.getParameter("username");
			String status = request.getParameter("status");
			if(now != null) {
				pageNow = Integer.parseInt(now);
			}
			if(loginName !=null || userName != null || status != null) {
				User user = new User();
				if(loginName != null) {
					user.setLoginName(loginName);
				}
				if(userName != null) {
					user.setUsername(userName);
				}
				if(status != "" && status != null) {
					user.setStatus(Integer.parseInt(status));
				}
				request.setAttribute("user", user);
				pb = userService.findBySome(pageNow, user);
			}else {
				pb = userService.findByPage(pageNow);
			}
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/user/userlist.jsp").forward(request, response);
		}else if("userdel.action".equals(action)) {
			boolean flag = false;
			String[] strs = request.getParameterValues("userIds");
			for (String string : strs) {
				int id = Integer.parseInt(string);
				flag = userService.delete(id);
			}
			if(flag) {
				request.getRequestDispatcher("/userlist.action").forward(request, response);
			}
		}else if("viewUser.action".equals(action)) {
			int id = Integer.parseInt(request.getParameter("updateid"));
			User user = userService.findByOne(id);
			if(user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/jsp/user/useredit.jsp").forward(request, response);
			}
		}else if("useredit.action".equals(action)) {
			User user = new User();
			user.setId(Integer.parseInt(request.getParameter("id")));
			user.setUsername(request.getParameter("username"));
			user.setStatus(Integer.parseInt(request.getParameter("status")));
			user.setLoginName(request.getParameter("loginname"));
			user.setPassword(request.getParameter("password"));
			boolean flag = userService.update(user);
			if(flag) {
				response.sendRedirect(request.getServletContext().getContextPath()+"/userlist.action");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
