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
 * Servlet implementation class GetShopInfo
 */
@WebServlet("/GetShopInfo")
public class GetShopInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetShopInfo() {
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
		System.out.println("getShopInfo");
		String MsgType = request.getParameter("msgtype");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String rsString = "";
		if (MsgType.equals("getinfoByshop")) {
			String shopName = new String(request.getParameter("shopname").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			rsString = service.getShopInfo(shopName);
			System.out.println("+"+rsString+"+");
		}
		if (MsgType.equals("getinfoBycompany")) {
			String companyName = new String(request.getParameter("companyname").getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(companyName);
			Service service = new Service();
			rsString = service.getShopInfoBycop(companyName);
		}
		if(MsgType.equals("addshop")){
			System.out.println("addShopInfo");
			String shopname = new String(request.getParameter("shopName").getBytes("ISO-8859-1"),"UTF-8");
			String companyname =new String(request.getParameter("companyName").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			Boolean res = service.addShop(shopname, companyname);
			if (res) {
				rsString = "1";
			}else {
				rsString = "0";
			}
		}
		if (MsgType.equals("deleteshop")) {
			System.out.println("deletShopInfo");
			String shopname = new String(request.getParameter("shopName").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			Boolean res = service.deletShop(shopname);
			if (res) {
				rsString = "1";
			}else {
				rsString = "0";
			}
		}
		out.write(rsString);
		out.flush();
		out.close();
	}

}
