package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Enterprise;
import com.service.Service;

/**
 * Servlet implementation class EnterpriseMng
 */
@WebServlet("/EnterpriseMng")
public class EnterpriseMng extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterpriseMng() {
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
		System.out.println("EnterpriseMng");
		String MsgType = request.getParameter("msgtype");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String rsString = "";
		if (MsgType.equals("addEnterprise")) {
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterprise_name(new String(request.getParameter("enterpriseName").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setEnterprise_abbr(new String(request.getParameter("simpleName").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setEnterprise_type(new String(request.getParameter("enterpriseType").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setEnterprise_property(new String(request.getParameter("property").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setContact(new String(request.getParameter("contactName").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setTelephone(new String(request.getParameter("contactTel").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setAddress(new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8"));
			
			enterprise.setLegal_repr(new String(request.getParameter("legalName").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setLegal_tele(new String(request.getParameter("legalTel").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setWeb_site(new String(request.getParameter("website").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setCustoms_code(new String(request.getParameter("customsCode").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setCountry(new String(request.getParameter("country").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setCity(new String(request.getParameter("city").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setEmail(new String(request.getParameter("email").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setFax_tel(new String(request.getParameter("faxTel").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setTax_code(new String(request.getParameter("taxCode").getBytes("ISO-8859-1"),"UTF-8"));
			enterprise.setBusi_license(new String(request.getParameter("busiLicense").getBytes("ISO-8859-1"),"UTF-8"));
			Service service = new Service();
			Boolean res = service.addEnterprise(enterprise);
			if (res) {
				rsString = "1";
			}else {
				rsString = "0";
			}
		}
		if (MsgType.equals("searchByName")) {
			String name =  new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			rsString = service.getEnterprise("name",name);
		}
		if (MsgType.equals("searchByType")) {
			String type =  new String(request.getParameter("type").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			rsString = service.getEnterprise("type",type);
		}
		if (MsgType.equals("searchByDate")) {
			String startdate =  new String(request.getParameter("startdate").getBytes("ISO-8859-1"),"UTF-8");
			String enddate = new String(request.getParameter("enddate").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			rsString = service.getEnterprise("date",startdate+","+enddate);
		}
		if (MsgType.equals("searchByCode")) {
			String code =  new String(request.getParameter("code").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			rsString = service.getEnterprise("code",code);
		}
		out.write(rsString);
		out.flush();
		out.close();
	}

}
