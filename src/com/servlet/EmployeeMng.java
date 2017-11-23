package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Employee;
import com.service.Service;

/**
 * Servlet implementation class EmployeeMng
 */
@WebServlet("/EmployeeMng")
public class EmployeeMng extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeMng() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EmployeeMng");
		String MsgType = request.getParameter("msgtype");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String rsString = "";
		if (MsgType.equals("addemp")) {
			Employee employee = new Employee();
			employee.setName(new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setSex(new String(request.getParameter("sex").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setAddress(new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setBirthday(new String(request.getParameter("birthday").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setJobId(new String(request.getParameter("jobId").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setPhone(new String(request.getParameter("phone").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setIdentityCard(new String(request.getParameter("identitycard").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setEmail(new String(request.getParameter("email").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setJoinDate(new String(request.getParameter("joinDate").getBytes("ISO-8859-1"),"UTF-8"));
			employee.setDepartment(new String(request.getParameter("department").getBytes("ISO-8859-1"),"UTF-8"));
			employee.toString();
			Service service = new Service();
			Boolean res = service.addemp(employee);
			if (res) {
				rsString = "1";
			}else {
				rsString = "0";
			}
		}
		if (MsgType.equals("deleteemp")) {
			Service service = new Service();
			Boolean res = service.deleteEmp(request.getParameter("jobId"));
			if (res) {
				rsString = "1";
			}else {
				rsString = "0";
			}
		}
		if (MsgType.equals("getemp")) {
			String jobId = request.getParameter("jobId");
			Service service = new Service();
			rsString = service.getEmpInfo(jobId);
			System.out.println(rsString);
		}
		out.write(rsString);
		out.flush();
		out.close();
	}

}
