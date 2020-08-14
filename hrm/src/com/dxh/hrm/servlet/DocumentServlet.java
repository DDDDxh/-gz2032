package com.dxh.hrm.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dxh.hrm.entity.Document;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.entity.User;
import com.dxh.hrm.service.DocService;
import com.dxh.hrm.service.impl.DocServiceImpl;

@WebServlet({ "/documentServlet", "/documentlist.action","/documentadd.action","/documentaddsave.action","/removeDocument","/toupdateDocument","/updateDocument" })
public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DocService docService = new DocServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNow = 1;
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("documentlist.action".equals(action)) {
			PageBean<Document> pb = null;
			String now = request.getParameter("pageIndex");
			System.out.println(now);
			String title = request.getParameter("title");
			System.out.println(title);
			if(now != null) {
				pageNow = Integer.parseInt(now);
			}
			if(title != null) {
				Document doc = new Document();
				doc.setTitle(title);
				request.setAttribute("document", doc);
				pb = docService.findBySome(pageNow, doc);
			}else {
				pb = docService.findByPage(pageNow);
			}
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/document/documentlist.jsp").forward(request, response);
		}else if("documentadd.action".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/jsp/document/documentadd.jsp").forward(request, response);
		}else if("documentaddsave.action".equals(action)) {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart) {
				FileItemFactory fileItemFactory = new DiskFileItemFactory();
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				Document doc = new Document();
				boolean flag = false;
				try {
					List<FileItem> list = fileUpload.parseRequest(request);
					if(list != null) {
						for (FileItem fileItem : list) {
							if(fileItem.isFormField()) {
								if("title".equals(fileItem.getFieldName())) {
									doc.setTitle(fileItem.getString("utf-8"));
								}
								if("remark".equals(fileItem.getFieldName())) {
									doc.setRemark(fileItem.getString("utf-8"));
								}
							}else {
								String path = request.getServletContext().getRealPath("/upload");
								System.out.println(path);
								File file = new File(path);
								if(!file.exists()) {
									file.mkdirs();
								}else {
									String fileName = fileItem.getName();
									File newFile = new File(file, fileName);
									fileItem.write(newFile);
									InputStream in = new FileInputStream(newFile);
									byte[] data = inputStreamToByte(in);
									doc.setFilebytes(data);
									doc.setFilename(fileName);
									doc.setFiletype("正常");
								}
							}
						}
						flag = true;
					}
					if(flag) {
						HttpSession session = request.getSession();
						User user = (User) session.getAttribute("user_session");
						System.out.println(user);
						doc.setCreatedate(new Timestamp(new Date().getTime()));
						doc.setUser(user);
						boolean isSuccess = docService.insert(doc);
						if(isSuccess) {
							System.out.println("插入成功");
							response.sendRedirect(request.getServletContext().getContextPath()+"/documentlist.action");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if("removeDocument".equals(action)) {
			boolean flag = false;
			String[] strs = request.getParameterValues("ids");
			for (String str : strs) {
				flag = docService.delete(Integer.parseInt(str));
			}
			if(flag) {
				response.sendRedirect(request.getServletContext().getContextPath()+"/documentlist.action");
			}
		}else if("toupdateDocument".equals(action)) {
			String id = request.getParameter("id");
			Document doc = docService.findByOne(Integer.parseInt(id));
			request.setAttribute("document", doc);
			request.getRequestDispatcher("/WEB-INF/jsp/document/showUpdateDocument.jsp").forward(request, response);
		}else if("updateDocument".equals(action)) {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart) {
				FileItemFactory fileItemFactory = new DiskFileItemFactory();
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				Document doc = new Document();
				boolean flag = false;
				try {
					List<FileItem> list = fileUpload.parseRequest(request);
					if(list != null) {
						for (FileItem fileItem : list) {
							if(fileItem.isFormField()) {
								if("id".equals(fileItem.getFieldName())) {
									doc.setId(Integer.parseInt(fileItem.getString()));
									System.out.println(doc.getId());
								}
								if("title".equals(fileItem.getFieldName())) {
									doc.setTitle(fileItem.getString("utf-8"));
								}
								if("remark".equals(fileItem.getFieldName())) {
									doc.setRemark(fileItem.getString("utf-8"));
								}
							}else {
								String path = request.getServletContext().getRealPath("/upload");
								System.out.println(path);
								File file = new File(path);
								if(!file.exists()) {
									file.mkdirs();
								}else {
									String fileName = fileItem.getName();
									File newFile = new File(file, fileName);
									fileItem.write(newFile);
									InputStream in = new FileInputStream(newFile);
									byte[] data = inputStreamToByte(in);
									doc.setFilebytes(data);
									doc.setFilename(fileName);
									doc.setFiletype("正常");
								}
							}
						}
						flag = true;
					}
					if(flag) {
						HttpSession session = request.getSession();
						User user = (User) session.getAttribute("user_session");
						System.out.println(user);
						doc.setUser(user);
						boolean isSuccess = docService.update(doc);
						if(isSuccess) {
							System.out.println("插入成功");
							response.sendRedirect(request.getServletContext().getContextPath()+"/documentlist.action");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private byte[] inputStreamToByte(InputStream in) throws IOException {
		ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			bAOutputStream.write(ch);
		}
		byte data[] = bAOutputStream.toByteArray();
		bAOutputStream.close();
		return data;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

