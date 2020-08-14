package com.dxh.hrm.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxh.hrm.entity.Notice;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.Type;
import com.dxh.hrm.entity.User;
import com.dxh.hrm.service.NoticeService;
import com.dxh.hrm.service.TypeService;
import com.dxh.hrm.service.impl.NoticeServiceImpl;
import com.dxh.hrm.service.impl.TypeServiceImpl;

@WebServlet({ "/NoticeServlet", "/noticelist.action","/toaddNotice","/noticesaveOrUpdate.action","/viewNotice.action","/noticedel.action" })
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NoticeService noticeService = new NoticeServiceImpl(); 
	TypeService typeService = new TypeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNow = 1;
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("noticelist.action".equals(action)) {
			PageBean<Notice> pb = null;
			String now = request.getParameter("pageIndex");
			String name = request.getParameter("name");
			if(now != null) {
				pageNow = Integer.parseInt(now);
			}
			if(name != null) {
				Notice notice = new Notice();
				notice.setName(name);
				request.setAttribute("notice", notice);
				pb = noticeService.findBySome(pageNow,notice);
			}else {
				pb = noticeService.findByPage(pageNow);
			}
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/noticelist.jsp").forward(request, response);
		}else if("toaddNotice".equals(action)) {
			List<Type> list = typeService.findAll();
			request.setAttribute("types", list);
			request.getRequestDispatcher("/WEB-INF/jsp/notice/notice_save_update.jsp").forward(request, response);
		}else if("noticesaveOrUpdate.action".equals(action)) {
			String id = request.getParameter("id");
			Notice notice = new Notice();
			if(id != "") {
				String content = request.getParameter("text");
				String name = request.getParameter("name");
				String typeId = request.getParameter("type_id");
				notice.setId(Integer.parseInt(id));
				notice.setName(name);
				notice.setModify_date(new Date());
				notice.setType(typeService.findById(Integer.parseInt(typeId)));
				notice.setContent(content);
				notice.setUser((User)request.getSession().getAttribute("user_session"));
				boolean flag = noticeService.update(notice);
				if(flag) {
					response.sendRedirect(request.getServletContext().getContextPath()+"/noticelist.action");
				}
			}else {
				String content = request.getParameter("text");
				String name = request.getParameter("name");
				String typeId = request.getParameter("type_id");
				notice.setName(name);
				notice.setCreateDate(new Date());
				notice.setType(typeService.findById(Integer.parseInt(typeId)));
				notice.setContent(content);
				notice.setUser((User)request.getSession().getAttribute("user_session"));
				notice.setModify_date(null);
				boolean flag = noticeService.insert(notice);
				if(flag) {
					response.sendRedirect(request.getServletContext().getContextPath()+"/noticelist.action");
				}
			}
		}else if("viewNotice.action".equals(action)) {
			String id = request.getParameter("id");
			Notice notice = noticeService.findByOne(Integer.parseInt(id));
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/toaddNotice").forward(request, response);
		}else if("noticedel.action".equals(action)) {
			boolean flag = false;
			String[] strs = request.getParameterValues("noticeIds");
			for (String str : strs) {
				flag = noticeService.delete(Integer.parseInt(str));
			}
			if(flag) {
				response.sendRedirect(request.getServletContext().getContextPath()+"/noticelist.action");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
