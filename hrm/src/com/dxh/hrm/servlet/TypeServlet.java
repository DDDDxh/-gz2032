package com.dxh.hrm.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.Type;
import com.dxh.hrm.entity.User;
import com.dxh.hrm.service.TypeService;
import com.dxh.hrm.service.impl.TypeServiceImpl;

@WebServlet({ "/TypeService", "/typelist.action","/addType.action","/typesaveOrUpdate.action","/viewType.action","/typedel.action" })
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TypeService typeService = new TypeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNow = 1;
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("typelist.action".equals(action)) {
			PageBean<Type> pb = null;
			String now = request.getParameter("pageIndex");
			String name = request.getParameter("name");
			System.out.println(name);
			if(now != null) {
				pageNow = Integer.parseInt(now);
			}
			if(name != null) {
				Type type = new Type();
				type.setName(name);
				request.setAttribute("type", type);
				pb = typeService.findBySome(pageNow,type);
			}else {
				pb = typeService.findByPage(pageNow);
			}
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/typelist.jsp").forward(request, response);
		}else if("addType.action".equals(action)){
			request.getRequestDispatcher("/WEB-INF/jsp/notice/type_save_update.jsp").forward(request, response);
		}else if("typesaveOrUpdate.action".equals(action)) {
			User user = (User) request.getSession().getAttribute("user_session");
			Type type = new Type();
			String id = request.getParameter("id");
			if(id !="") {
				type.setId(Integer.parseInt(id));
				type.setName(request.getParameter("name"));
				type.setModifyDate(new Date());
				type.setUser(user);
				boolean flag = typeService.update(type);
				if(flag) {
					response.sendRedirect(request.getServletContext().getContextPath()+"/typelist.action");
				}
			}else {
				String name = request.getParameter("name");
				type.setName(name);
				type.setCreateDate(new Date());
				type.setStatus(0);
				type.setUser(user);
				type.setModifyDate(null);
				boolean flag = typeService.insert(type);
				if(flag) {
					response.sendRedirect(request.getServletContext().getContextPath()+"/typelist.action");
				}
			}
		}else if("viewType.action".equals(action)) {
			String id = request.getParameter("id");
			Type type = typeService.findById(Integer.parseInt(id));
			request.setAttribute("type", type);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/type_save_update.jsp").forward(request, response);
		}else if("typedel.action".equals(action)) {
			boolean flag = false;
			String[] strs = request.getParameterValues("typeIds");
			for (String str : strs) {
				flag = typeService.delete(Integer.parseInt(str));
			}
			if(flag) {
				response.sendRedirect(request.getServletContext().getContextPath()+"/typelist.action");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
