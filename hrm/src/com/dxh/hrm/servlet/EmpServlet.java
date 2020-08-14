package com.dxh.hrm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxh.hrm.entity.Department;
import com.dxh.hrm.entity.Employee;
import com.dxh.hrm.entity.Job;
import com.dxh.hrm.entity.PageBean;
import com.dxh.hrm.service.DeptService;
import com.dxh.hrm.service.EmpService;
import com.dxh.hrm.service.JobService;
import com.dxh.hrm.service.impl.DeptServiceImpl;
import com.dxh.hrm.service.impl.EmpServiceImpl;
import com.dxh.hrm.service.impl.JobServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet({ "/empServelt", "/employeelist.action","/employeeadd.action","/addEmployee","/getcardid.action","/employeedel.action","/toupdateEmployee","/updateEmployee" })
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpService empService = new EmpServiceImpl();
	DeptService deptService = new DeptServiceImpl();
	JobService jobService = new JobServiceImpl();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNow = 1;
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		if("employeelist.action".equals(action)) {
			PageBean<Employee> pb = null;
			String jobId= request.getParameter("job_id");
			String name = request.getParameter("name");
			String cardId = request.getParameter("cardId");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			String deptId = request.getParameter("dept_id");
			String now = request.getParameter("pageIndex");
			if(now != null) {
				pageNow = Integer.parseInt(now);
			}
			if(jobId != null || name != null || cardId != null || sex != null || phone != null || deptId != null) {
				Employee emp1 = new Employee();
				if(jobId != "") {
					int id = Integer.parseInt(jobId);
					emp1.setJob(jobService.findByOne(id));
				}
				if(name != null) {
					emp1.setName(name);
				}
				if(cardId != null) {
					emp1.setCardId(cardId);
				}
				if(sex != "") {
					emp1.setSex(Integer.parseInt(sex));
				}
				if(phone!=null) {
					emp1.setPhone(phone);
				}
				if(deptId != "") {
					emp1.setDept(deptService.findByOne(Integer.parseInt(deptId)));
				}
				request.setAttribute("emp", emp1);
				pb = empService.findBySome(pageNow, emp1);
			}else {
				pb = empService.findByPage(pageNow);
			}
			List<Department> deptlist = deptService.findAll();
			List<Job> joblist= jobService.findAll(); 
			request.setAttribute("deptList", deptlist);
			request.setAttribute("jobList", joblist);
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/WEB-INF/jsp/employee/employeelist.jsp").forward(request, response);
		}else if("employeeadd.action".equals(action)) {
			List<Department> deptlist = deptService.findAll();
			List<Job> joblist= jobService.findAll(); 
			request.setAttribute("deptList", deptlist);
			request.setAttribute("jobList", joblist);
			request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeadd.jsp").forward(request, response);
		}else if("addEmployee".equals(action)) {
			System.out.println("进来的");
			Employee emp = new Employee();
			emp.setName(request.getParameter("name"));
			emp.setCardId(request.getParameter("cardId"));
			emp.setSex(Integer.parseInt(request.getParameter("sex")));
			emp.setJob(jobService.findByOne(Integer.parseInt(request.getParameter("job_id"))));
			emp.setEducation(request.getParameter("education"));
			emp.setEmail(request.getParameter("email"));
			emp.setPhone(request.getParameter("phone"));
			emp.setTel(request.getParameter("tel"));
			emp.setParty(request.getParameter("party"));
			emp.setQqNum(request.getParameter("qqNum"));
			emp.setAddress(request.getParameter("address"));
			emp.setPostCode(request.getParameter("postCode"));
			String birthday = request.getParameter("birthday");
			Timestamp timestamp = Timestamp.valueOf(birthday);
			emp.setBrithday(timestamp);
			emp.setRace(request.getParameter("race"));
			emp.setSpeciality(request.getParameter("speciality"));
			emp.setHobby(request.getParameter("hobby"));
			emp.setRemark(request.getParameter("remark"));
			emp.setDept(deptService.findByOne(Integer.parseInt(request.getParameter("dept_id"))));
			boolean flag = empService.insert(emp);
				if(flag) {
					response.sendRedirect(request.getServletContext().getContextPath()+"/employeelist.action");
				}			
		}else if("getcardid.action".equals(action)) {
			PrintWriter out = response.getWriter();
			String cardId = request.getParameter("iscardId");
			boolean flag = empService.findByCardId(cardId);
			ObjectMapper mapper = new ObjectMapper();
			if(flag) {
				out.print(mapper.writeValueAsString(flag));
			}
		}else if("employeedel.action".equals(action)) {
			boolean flag = false ;
			String[] strs = request.getParameterValues("empIds");
			for (String str : strs) {
				flag = empService.delete(Integer.parseInt(str));
				
			}
			if(flag) {
				response.sendRedirect(request.getServletContext().getContextPath()+"/employeelist.action");
			}
		}else if("toupdateEmployee".equals(action)) {
			String id = request.getParameter("updateid");
			System.out.println(id);
			Employee emp = empService.findByOne(Integer.parseInt(id));
			System.out.println(emp);
			List<Department> deptlist = deptService.findAll();
			List<Job> joblist= jobService.findAll(); 
			request.setAttribute("deptList", deptlist);
			request.setAttribute("jobList", joblist);
			request.setAttribute("employee", emp);
			request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeedit.jsp").forward(request, response);
		}else if("updateEmployee".equals(action)) {
			System.out.println("进来的");
			Employee emp = new Employee();
			emp.setId(Integer.parseInt(request.getParameter("id")));
			emp.setName(request.getParameter("name"));
			emp.setCardId(request.getParameter("cardId"));
			emp.setSex(Integer.parseInt(request.getParameter("sex")));
			emp.setJob(jobService.findByOne(Integer.parseInt(request.getParameter("job_id"))));
			emp.setEducation(request.getParameter("education"));
			emp.setEmail(request.getParameter("email"));
			emp.setPhone(request.getParameter("phone"));
			emp.setTel(request.getParameter("tel"));
			emp.setParty(request.getParameter("party"));
			emp.setQqNum(request.getParameter("qqNum"));
			emp.setAddress(request.getParameter("address"));
			emp.setPostCode(request.getParameter("postCode"));
			String birthday = request.getParameter("birthday");
			Timestamp timestamp = Timestamp.valueOf(birthday);
			emp.setBrithday(timestamp);
			emp.setRace(request.getParameter("race"));
			emp.setSpeciality(request.getParameter("speciality"));
			emp.setHobby(request.getParameter("hobby"));
			emp.setRemark(request.getParameter("remark"));
			emp.setDept(deptService.findByOne(Integer.parseInt(request.getParameter("dept_id"))));
			boolean flag = empService.update(emp);
				if(flag) {
					response.sendRedirect(request.getServletContext().getContextPath()+"/employeelist.action");
				}			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
