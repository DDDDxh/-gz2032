package com.dxh.hrm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxh.hrm.entity.User;


@WebFilter("/*")
public class LoginFilter implements Filter {
	
	String[] urls = {"login","css","js"};

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp; 
		String path = request.getRequestURI();
		boolean flag = false;
		for(String url : urls) {
			if(path.indexOf(url)!=-1) {
				flag = true;
				break;
			}
		}
		if(flag) {
			chain.doFilter(request, response);			
		}else {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user_session");
			if(user != null && user.getLoginName()!=null) {
				chain.doFilter(request, response);	
			}else {
				response.sendRedirect(request.getServletContext().getContextPath()+"/loginForm.action");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
