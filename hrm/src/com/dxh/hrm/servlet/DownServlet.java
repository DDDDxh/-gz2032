package com.dxh.hrm.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxh.hrm.entity.Document;
import com.dxh.hrm.service.DocService;
import com.dxh.hrm.service.impl.DocServiceImpl;

@WebServlet("/documentdownload.action")
public class DownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DocService docServic = new DocServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Document doc = docServic.findByOne(Integer.parseInt(id));
		String fileName = doc.getFilename();
		byte[] bytes = doc.getFilebytes();
		//根据不同浏览器进行不同的编码
		String fileNameEncoder = URLEncoder.encode(fileName, "utf-8");    

		//文件输入流读取文件内容
		InputStream in = new ByteArrayInputStream(bytes);
		//设置浏览器下载模式
		response.setHeader("Content-Disposition", "attachment;filename="+fileNameEncoder);
		//创建一个输出流
		ServletOutputStream out = response.getOutputStream();
		//从输出流中将内容输出到客户端
		int len = 0;
		byte[] by = new byte[1024];
		while((len=in.read(by))>0){
			out.write(by, 0, len);
		}
		//关闭流
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
