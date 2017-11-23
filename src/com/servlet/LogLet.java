package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Employee;
import com.model.Enterprise;
import com.model.Storage;
import com.service.Service;
import com.util.MD5;
import com.util.PropertiesUtil;

/**
 * Servlet implementation class LogLet
 */
@WebServlet("/LogLet")
public class LogLet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogLet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收客户端信息
		String MsgType = request.getParameter("msgtype");
		String SecretKey = request.getParameter("key");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String rsString = "";
		String trKey = PropertiesUtil.getKey(this);
		System.out.println("getkey="+SecretKey+"   trkey="+trKey);
		if (MsgType.equals("login")
				&&MD5.checkMSG(SecretKey, trKey)) {
			String username = request.getParameter("username");
			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
			String password = request.getParameter("password");
			System.out.println(username + "--" + password);

			// 新建服务对象
			Service serv = new Service();

			// 验证处理
			String loged = serv.login(username, password);
			if (loged.equals("")) {
				System.out.print("Failed");
				rsString = "0";

			} else {
				System.out.print("Succss");
				rsString = loged;
			}
		}
		if (MsgType.equals("changpsw")) {
			String username = request.getParameter("username");
			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
			String newpsw = request.getParameter("newpsw");
			System.out.println("newpsw:" + newpsw);
			Service service = new Service();
			// 验证处理
			boolean loged = service.changepsw(username, newpsw);
			if (loged) {
				System.out.print("Succss");
				rsString = "1";
			} else {
				System.out.print("Failed");
				rsString = "0";
			}
		}
		//employeeMng
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
		
		//enterpriseMng
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
		
		//getshopInfo
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
		
		//orderdetail
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
		
		//storageMng
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
		if (MsgType.equals("getprestorage")) {
			Service service = new Service();
			rsString = service.getBoundedNo();
		}
		if (MsgType.equals("storage")) {
			//获取保税号 商品条形码 货号 仓位 数量 保质期 操作用户名
			String bondedNo = new String(request.getParameter("bondedNo").getBytes("ISO-8859-1"),"UTF-8");
			String goodsBarCode = new String(request.getParameter("goodsBarCode").getBytes("ISO-8859-1"),"UTF-8");
			String itemNo = new String(request.getParameter("itemNo").getBytes("ISO-8859-1"),"UTF-8");
			String storageBox = new String(request.getParameter("storageBox").getBytes("ISO-8859-1"),"UTF-8");
			String quantity = new String(request.getParameter("quantity").getBytes("ISO-8859-1"),"UTF-8");
			String remark = new String(request.getParameter("remark").getBytes("ISO-8859-1"),"UTF-8");
			String account = new String(request.getParameter("account").getBytes("ISO-8859-1"),"UTF-8");
			//检测该保税号下是否有对应备案商品
			Service service = new Service();
			Storage storage =service.checkStorage(bondedNo,goodsBarCode);
			if (!(storage.getGoods_record_code().equals(""))) {
				//执行定仓操作
				if (service.doStorage(storage, quantity, storageBox, account,remark)) {
					rsString = "定仓成功";
				}else {
					rsString = "请核对信息";
				}
				
			}else {
				rsString = "该保税号下未找到备案商品";
			}
		}
		//根据条形码获取货号
		if (MsgType.equals("getgoodsbar")) {
			String bondedNo = new String(request.getParameter("bondedNo").getBytes("ISO-8859-1"),"UTF-8");
			String goodsBarCode = new String(request.getParameter("goodsBarCode").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			Storage storage =service.checkStorage(bondedNo,goodsBarCode);
			rsString = storage.getGoods_record_code();
		}
		//根据仓位号+条形码获取备案商品
		if (MsgType.equals("getstorage")) {
			String goodsBarCode = new String(request.getParameter("goodsBarCode").getBytes("ISO-8859-1"),"UTF-8");
			String prePosition = new String(request.getParameter("prePosition").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			rsString = service.findByPositionGoodsBarCode(prePosition, goodsBarCode);
		}
		//调仓
		if (MsgType.equals("dochangewarhouse")) {
			String storageId = new String(request.getParameter("storageId").getBytes("ISO-8859-1"),"UTF-8");
			String quantity = new String(request.getParameter("quantity").getBytes("ISO-8859-1"),"UTF-8");
			String targetPosition = new String(request.getParameter("targetPosition").getBytes("ISO-8859-1"),"UTF-8");
			String remark = new String(request.getParameter("remark").getBytes("ISO-8859-1"),"UTF-8");
			String account = new String(request.getParameter("account").getBytes("ISO-8859-1"),"UTF-8");
			Service service = new Service();
			rsString = service.doChangeWareHouse(quantity, targetPosition, remark, storageId, account);
		}
		// 返回信息到客户端
		out.write(rsString);
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
