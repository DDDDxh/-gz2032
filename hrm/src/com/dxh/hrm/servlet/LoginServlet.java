package com.dxh.hrm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxh.hrm.entity.User;
import com.dxh.hrm.service.UserService;
import com.dxh.hrm.service.impl.UserServiceImpl;

@WebServlet({ "/loginForm.action", "/login.action","/logout.action" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取到请求路径
		String uri = request.getRequestURI();
		//截取请求名
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if(action.equals("login.action")){
			//获取用户的操作
			String loginName = request.getParameter("loginname");
			String pwd = request.getParameter("password");
			User user = new User();
			user.setLoginName(loginName);
			user.setPassword(pwd);
			User isExist = userService.findByOne(user);
			if(isExist == null){
				request.setAttribute("message", "用户名或密码错误");
				request.getRequestDispatcher("/loginForm.action").forward(request, response);
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("user_session", isExist);
				//跳转到主界面
				request.getRequestDispatcher("/main.action").forward(request, response);
				
			}
		}else if("logout.action".equals(action)) {
			HttpSession session = request.getSession();
			session.setAttribute("user_session", null);
			response.sendRedirect(request.getServletContext().getContextPath()+"/loginForm.action");
		}else if("loginForm.action".equals(action)){
			//请求登录界面
			request.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(request, response);
		}
	}

}
