package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

/**
 * Servlet implementation class StorageMng
 */
@WebServlet("/StorageMng")
public class StorageMng extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StorageMng() {
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
		System.out.println("StorageMng");
		String MsgType = request.getParameter("msgtype");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String rsString = "";
		if (MsgType.equals("getEnterpriseName")) {
			Service service = new Service();
			rsString = service.getEnterprise();
		}
		if (MsgType.equals("getPreStorage")) {
			Service service = new Service();
			String enterprise_abbr = new String(request.getParameter("enterpriseName").getBytes("ISO-8859-1"),"UTF-8");
			String startDate = new String(request.getParameter("startDate").getBytes("ISO-8859-1"),"UTF-8");
			String endDate = new String(request.getParameter("endDate").getBytes("ISO-8859-1"),"UTF-8");
			rsString = service.getPreStorageInfo(enterprise_abbr, startDate, endDate);
		}
		out.write(rsString);
		out.flush();
		out.close();
	}

}
