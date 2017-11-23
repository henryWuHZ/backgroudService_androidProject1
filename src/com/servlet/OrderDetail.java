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
 * Servlet implementation class OrderDetail
 */
@WebServlet("/OrderDetail")
public class OrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetail() {
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
		System.out.println("OrderDetail");
		String MsgType = request.getParameter("msgtype");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String rsString = "";
		if (MsgType.equals("getOrderInfoByExpress")) {
			Service service = new Service();
			rsString = service.getOrderInfo("express", new String(request.getParameter("expressId").getBytes("ISO-8859-1"),"UTF-8"));
			System.out.println(rsString);
		}
		if (MsgType.equals("getOrderInfoByExternal")) {
			Service service = new Service();
			rsString = service.getOrderInfo("external", new String(request.getParameter("externalId").getBytes("ISO-8859-1"),"UTF-8"));
			System.out.println(rsString);
		}
		out.write(rsString);
		out.flush();
		out.close();
	}

}
