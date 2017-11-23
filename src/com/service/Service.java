package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.model.Employee;
import com.model.Enterprise;
import com.model.EnterpriseRecord;
import com.model.OrderState;
import com.model.PreStorageInfo;
import com.model.ShopInfo;
import com.model.Storage;
import com.model.StorageModel;
import com.sun.corba.se.impl.naming.pcosnaming.NameServer;
import com.util.MD5;

import JdbcConnnect.DBManager;

public class Service {
	//登录验证
	public String login(String username, String password) {

        // 获取Sql查询语句
        String logSql = "SELECT username,password,level FROM user a , employee b , organization c WHERE a.employee = b.employee_id AND b.organization = c.organization_id AND a.username ='"+username+"'";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        String reString = "";
        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
            	String relPwd = rs.getString(2);
            	System.out.println(relPwd);
            	if (MD5.checkMSG(password, relPwd)) {
            		reString=rs.getString(3);
                	System.out.println("level+"+reString);
                    sql.closeDB(rs);
                    return reString;
				}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
    	System.out.println("level+"+reString);
        return reString;
    }
	//注册用户
    public Boolean register(String username, String password) {
        // 获取Sql查询语句
        String regSql = "insert into user (username,password,employee,role) values('"+ username+ "','"+ password+ "',(SELECT employee_id FROM employee a WHERE a.job_number = '"+username+"'),'1') ";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        return false;
    }
    //删除用户
    public Boolean deleteUser(String username) {
    	 // 获取Sql查询语句
        String regSql = "DELETE FROM user WHERE username ='"+username+"'";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        return false;
	}
    //修改密码
    public Boolean changepsw(String username,String newpsw) {
    	String regSql = "UPDATE user SET password='"+newpsw+"' WHERE (username='"+username+"')";
    	// 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
    	return false;
		
	}
    //按店铺名获取店铺备案企业信息
    public String getShopInfo(String shopName) {
		String regSql = "SELECT	a.store_name,b.enterprise_name FROM ds_store a, enterprise b ,enterprise_record c WHERE a.enterprise_record = c.enterprise_record_id AND c.enterprise = b.enterprise_id AND a.store_name LIKE '%"+shopName+"%'";
    	DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	List<ShopInfo> list = new ArrayList<>();
    	String jString = "";
    	try {
			ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				ShopInfo shopinfo = new ShopInfo();
				System.out.println(rSet.getString(1)+"     "+rSet.getString(2));
				shopinfo.setShopName(rSet.getString(1));
				shopinfo.setCompanyName(rSet.getString(2));
				list.add(shopinfo);
			}
			sql.closeDB(rSet);
			jString = JSON.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sql.closeDB();
		}
		return jString;
	}
    //按企业名获取店铺备案企业信息
    public String getShopInfoBycop(String companyName) {
    	String regSql = "SELECT	a.store_name,b.enterprise_name FROM ds_store a, enterprise b ,enterprise_record c WHERE a.enterprise_record = c.enterprise_record_id AND c.enterprise = b.enterprise_id AND b.enterprise_name LIKE '%"+companyName+"%'";
    	DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	List<ShopInfo> list = new ArrayList<>();
    	String jString = "";
    	try {
			ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				ShopInfo shopinfo = new ShopInfo();
				System.out.println(rSet.getString(1)+"     "+rSet.getString(2));
				shopinfo.setShopName(rSet.getString(1));
				shopinfo.setCompanyName(rSet.getString(2));
				list.add(shopinfo);
			}
			sql.closeDB(rSet);
			jString = JSON.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sql.closeDB();
		}
		return jString;
	}
    //删除店铺
    public Boolean deletShop(String shopname) {
		String regSql="DELETE FROM ds_store WHERE store_name='"+shopname+"'";
		// 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
    	return false;
	}
    //添加店铺
    public Boolean addShop(String shopname,String companyName) {
    	String regSql="INSERT INTO ds_store (enterprise_record,store_name) VALUES 	((SELECT enterprise_record_id FROM enterprise_record b WHERE b.enterprise = (SELECT enterprise_id FROM enterprise WHERE enterprise_name='"+companyName+"')),'"+shopname+"')";
		// 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
    	return false;
	}
    //添加新员工
    public Boolean addemp (Employee employee) {
    	//添加员工信息
			String regSql = "INSERT INTO employee (employee_name,gender,birth_date,job_number,home_address,identity_card_number,phone_number,email,join_date,organization)VALUES ('"+employee.getName()+"','"+employee.getSex()+"','"+employee.getBirthday()+"','"+employee.getJobId()+"','"+employee.getAddress()+"','"+employee.getIdentityCard()+"','"+employee.getPhone()+"','"+employee.getEmail()+"','"+employee.getJoinDate()+"',(SELECT organization_id FROM organization a WHERE a.organization_code = '"+employee.getDepartment()+"'))";
			// 获取DB对象
			System.out.println(regSql);
	        DBManager sql = DBManager.createInstance();
	        sql.connectDB();
	        int ret = sql.executeUpdate(regSql);
	        if (ret != 0) {
	        	//分配账号
	        	if(register(employee.getJobId(), "e10adc3949ba59abbe56e057f20f883e")){
	        		sql.closeDB();
	        		return true;
	            }else {
					return false;
				}
	        }else {
	        	sql.closeDB();
		    	return false;
			}	        
	}
    //删除员工信息
    public Boolean deleteEmp(String jobId) {
		if (deleteUser(jobId)) {
			String regSql="DELETE FROM employee WHERE job_number ='"+jobId+"'";
			// 获取DB对象
	        DBManager sql = DBManager.createInstance();
	        sql.connectDB();
	        int ret = sql.executeUpdate(regSql);
	        if (ret != 0) {
	            sql.closeDB();
	            return true;
	        }
	        sql.closeDB();
	    	return false;
		}else{
			return false;
			}
	}
    //获取员工信息
    public String getEmpInfo(String jobId) {
    	String regSql = "SELECT a.employee_name,a.gender,a.birth_date,a.job_number,a.home_address,a.identity_card_number,a.phone_number,a.email,a.join_date,b.organization_code FROM employee a , organization b WHERE a.organization=b.organization_id AND job_number ='"+jobId+"'";
    	DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	String jString = "";
    	List<Employee> list = new ArrayList<>();
    	try {
			ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				Employee employee = new Employee();
				System.out.println(rSet.toString());
				employee.setName(rSet.getString(1));
				employee.setSex(rSet.getString(2));
				employee.setBirthday(rSet.getString(3));
				employee.setJobId(rSet.getString(4));
				employee.setAddress(rSet.getString(5));
				employee.setIdentityCard(rSet.getString(6));
				employee.setPhone(rSet.getString(7));
				employee.setEmail(rSet.getString(8));
				employee.setJoinDate(rSet.getString(9));
				employee.setDepartment(rSet.getString(10));
				System.out.println(employee.toString());
				list.add(employee);
			}
			sql.closeDB(rSet);
			jString = JSON.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sql.closeDB();
		}
		return jString;
		
	}
    //企业备案
    public Boolean addEnterprise(Enterprise enterprise) {
    	String regSql="INSERT INTO enterprise (Enterprise_name,Enterprise_abbr,Enterprise_type,Enterprise_property,Contact,Telephone,Address,Legal_repr,Legal_tele,Web_site,Customs_code,Country,City,Email,Fax_tel,Tax_code,Busi_license)VALUES ('"+enterprise.getEnterprise_name()+"','"+enterprise.getEnterprise_abbr()+"','"+enterprise.getEnterprise_type()+"','"+enterprise.getEnterprise_property()+"','"+enterprise.getContact()+"','"+enterprise.getTelephone()+"','"+enterprise.getAddress()+"','"+enterprise.getLegal_repr()+"','"+enterprise.getLegal_tele()+"','"+enterprise.getWeb_site()+"','"+enterprise.getCustoms_code()+"','"+enterprise.getCountry()+"','"+enterprise.getCity()+"','"+enterprise.getEmail()+"','"+enterprise.getFax_tel()+"','"+enterprise.getTax_code()+"','"+enterprise.getBusi_license()+"')";
		// 获取DB对象
    	System.out.println(regSql);
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
    	return false;
	}
    //获取备案企业信息
    public String getEnterprise(String msg,String arg) {
    	String regSql = "";
    	if (msg.equals("name")){
    		regSql = "SELECT a.enterprise_name,b.enterprise_record_code,b.enterprise_record_type,b.record_date,b.distribution_enable FROM enterprise a,enterprise_record b WHERE b.enterprise = a.enterprise_id AND a.enterprise_name LIKE '%"+arg+"%'";
    	}else if (msg.equals("date")){
    		String[] dates = arg.split(",");
    		System.out.println(dates[0]+","+dates[1]);
    		regSql = "SELECT a.enterprise_name,b.enterprise_record_code,b.enterprise_record_type,b.record_date,b.distribution_enable FROM enterprise a,enterprise_record b WHERE b.enterprise = a.enterprise_id AND b.record_date between  '"+dates[0]+"' AND '"+dates[1]+"'";
		}else if (msg.equals("code")) {
    		regSql = "SELECT a.enterprise_name,b.enterprise_record_code,b.enterprise_record_type,b.record_date,b.distribution_enable FROM enterprise a,enterprise_record b WHERE b.enterprise = a.enterprise_id AND b.enterprise_record_code =  '"+arg+"'";
		}else if (msg.equals("type")) {
			String enterprise_record_type = "";
			if (arg.equals("备货")) {
				enterprise_record_type= "1";
			}else if (arg.equals("集货")) {
				enterprise_record_type= "2";
			}else if (arg.equals("备货和集货")){
				enterprise_record_type= "4";
			}
    		regSql = "SELECT a.enterprise_name,b.enterprise_record_code,b.enterprise_record_type,b.record_date,b.distribution_enable FROM enterprise a,enterprise_record b WHERE b.enterprise = a.enterprise_id AND b.enterprise_record_type =  '"+enterprise_record_type+"'";
		}
    	DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	String jString = "";
    	List<EnterpriseRecord> list = new ArrayList<>();
    	try {
			ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				EnterpriseRecord enterpriseRecord = new EnterpriseRecord();
				enterpriseRecord.setName(rSet.getString(1));
				enterpriseRecord.setCode(rSet.getString(2));
				enterpriseRecord.setType(rSet.getString(3));
				enterpriseRecord.setDate(rSet.getString(4));
				enterpriseRecord.setDistribution_enable(rSet.getString(5));
				System.out.println(enterpriseRecord.toString());
				list.add(enterpriseRecord);
			}
			sql.closeDB(rSet);
			jString = JSON.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sql.closeDB();
		}
		return jString;	
	}
    
    public String getEnterprise(){
    	String regSql ="SELECT a.enterprise_abbr FROM enterprise a,enterprise_record b WHERE b.enterprise = a.enterprise_id";
    	DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	StringBuffer rStringBuffer = new StringBuffer();
    	try {
    		ResultSet rSet= sql.executeQuery(regSql);
    		while (rSet.next()) {
    			if (!(rSet.getString(1).equals(""))) {
    				rStringBuffer.append(rSet.getString(1));
        			rStringBuffer.append(",");
				}
    		}
    		sql.closeDB(rSet);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sql.closeDB();
		}
    	return rStringBuffer.toString();
    }
    //订单查询
    public String getOrderInfo(String msg,String arg) {
    	String regSql = "";
		if (msg.equals("external")) {
			regSql ="SELECT a.externalno ,a.express_ID,c.enterprise_abbr,a.order_state,a.customs_state,a.inspection_state,a.out_state,a.recheck_state,a.distribution_status FROM external_order a,enterprise_record b,enterprise c WHERE a.enterprise_record = b.enterprise_record_id AND b.enterprise = c.enterprise_id AND a.externalno = '"+arg+"'";
		}else {
			regSql ="SELECT a.externalno ,a.express_ID,c.enterprise_abbr,a.order_state,a.customs_state,a.inspection_state,a.out_state,a.recheck_state,a.distribution_status FROM external_order a,enterprise_record b,enterprise c WHERE a.enterprise_record = b.enterprise_record_id AND b.enterprise = c.enterprise_id AND a.express_ID = '"+arg+"'";
		}
		DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	String jString = "";
    	List<OrderState> list = new ArrayList<>();
    	try {
			ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				OrderState orderState = new OrderState();
				orderState.setExternalId(rSet.getString(1));
				orderState.setExpressId(rSet.getString(2));
				orderState.setShopName(rSet.getString(3));
				orderState.setOrder_state(rSet.getString(4));
				orderState.setCustoms_state(rSet.getString(5));
				orderState.setInspection_state(rSet.getString(6));
				orderState.setOut_state(rSet.getString(7));
				orderState.setRecheck_state(rSet.getString(8));
				orderState.setDistribution_status(rSet.getString(9));
				list.add(orderState);
			}
			sql.closeDB(rSet);
			jString = JSON.toJSONString(list);
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				sql.closeDB();
			}
			return jString;	
	}
    //预入库查询
    public String getPreStorageInfo(String enterprise_abbr,String startDate,String endDate) {
    	String regSql = "SELECT DISTINCT a.bonded_no,b.enterprise_abbr,a.freight_forwarding,a.confirmed,a.received_date FROM pre_storage a,enterprise b WHERE b.enterprise_abbr ='"+enterprise_abbr+"' AND a.work_order IN (SELECT work_order_id FROM work_order WHERE work_order.enterprise_record=(SELECT enterprise_record_id FROM enterprise_record WHERE enterprise_record.enterprise =(SELECT enterprise_id FROM enterprise WHERE enterprise.enterprise_abbr = '"+enterprise_abbr+"')))AND a.received_date BETWEEN '"+startDate+"' AND '"+endDate+"'";
    	DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	String jString = "";
    	List<PreStorageInfo> list = new ArrayList<>();
    	try {
    		ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				PreStorageInfo preStorage = new PreStorageInfo();
				preStorage.setBonded_no(rSet.getString(1));
				preStorage.setStore_name(rSet.getString(2));
				preStorage.setFreight_forwarding(rSet.getString(3));
				preStorage.setConfirmed(rSet.getString(4));
				preStorage.setConfirmed_date(rSet.getString(5));
				list.add(preStorage);
			}
			sql.closeDB(rSet);
			jString = JSON.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
			sql.closeDB();
		}
    	return jString;
	}
    //获取未入库保税号
    public String getBoundedNo() {
    	String regSql = "SELECT a.bonded_no FROM pre_storage a WHERE a.confirmed='0'";
    	DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	StringBuffer jString = new StringBuffer();
    	try {
    		ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				jString.append(rSet.getString(1));
				jString.append(",");
			}
			sql.closeDB(rSet);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
			sql.closeDB();
		}
    	return jString.toString();
	}
    //检测该保税号下是否有对应备案商品
    public Storage checkStorage(String bondedNo,String goodsBarCode) {
    	goodsBarCode = goodsBarCode.replaceAll("\r|\n", "");
    	System.out.println(goodsBarCode);
		String regSql = "SELECT a.bonded_no,c.goods_record_id,c.goods_record_code,d.goods_barcode,c.apply_enterprise FROM pre_storage a,pre_storage_detail b,goods_record c,goods d WHERE a.pre_storage_id=b.pre_storage AND a.bonded_no='"+bondedNo+"' AND b.goods_record=c.goods_record_id AND d.goods_barcode='"+goodsBarCode+"' AND c.goods = d.goods_id ";		
		System.out.println(regSql);
		Storage storage = new Storage();
		DBManager sql = DBManager.createInstance();
    	sql.connectDB();
    	try {
    		ResultSet rSet= sql.executeQuery(regSql);
			if (rSet.next()) {
				storage.setGoods_record_id(rSet.getString(2));
				storage.setGoods_record_code(rSet.getString(3));
				storage.setApply_enterprise(rSet.getString(5));
				System.out.println(rSet.getString(3));
			}
			sql.closeDB(rSet);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
			sql.closeDB();
		}
    	return storage;
	}
    //执行定仓操作
    public boolean doStorage(Storage storage,String quantity,String storage_box,String account,String remark) {
    	String regSql;
    	if (remark.equals("")) {
    		regSql="INSERT INTO storage(goods_record,storage_box,enterprise_record,quantity,create_user) VALUES('"+storage.getGoods_record_id()+"',(SELECT a.storage_box_id FROM storage_box a WHERE a.storage_box_code='"+storage_box+"'),'"+storage.getApply_enterprise()+"','"+quantity+"','"+account+"')";
		}else {
    		regSql="INSERT INTO storage(goods_record,storage_box,enterprise_record,quantity,create_user,shelf_life) VALUES('"+storage.getGoods_record_id()+"',(SELECT a.storage_box_id FROM storage_box a WHERE a.storage_box_code='"+storage_box+"'),'"+storage.getApply_enterprise()+"','"+quantity+"','"+account+"','"+remark+"')";
		}
    	System.out.println(regSql);
		DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        return false;
	}
    //根据仓位+条形码获取备案商品
    public String findByPositionGoodsBarCode(String prePosition,String goodsBarCode) {
		String regSql = "SELECT s.storage_id,b.enterprise_abbr,c.goods_record_code,e.bonded_no,s.quantity,d.goods_desc,s.shelf_life FROM Storage s ,enterprise_record a,enterprise b,goods_record c,goods d,pre_storage e,storage_box f WHERE s.enterprise_record = a.enterprise_record_id AND a.enterprise = b.enterprise_id AND s.goods_record = c.goods_record_id AND c.goods = d.goods_id AND s.pre_storage = e.pre_storage_id AND s.storage_box = f.storage_box_id AND e.confirmed = '1' AND d.goods_barcode ='"+goodsBarCode+"' AND f.storage_box_code ='"+prePosition+"'";
		System.out.println(regSql);
		DBManager sql = DBManager.createInstance();
    	sql.connectDB();	
    	List<StorageModel> list = new ArrayList<>();
    	String jString = "";
    	try {
			ResultSet rSet= sql.executeQuery(regSql);
			while (rSet.next()) {
				StorageModel storageModel = new StorageModel();
				storageModel.setStorageId(Integer.parseInt(rSet.getString(1)));
				storageModel.setCompanyName(rSet.getString(2));
				storageModel.setGoodsRecordCode(rSet.getString(3));
				storageModel.setBondedNo(rSet.getString(4));
				storageModel.setQuantity(rSet.getInt(5));
				storageModel.setGoodsDesc(rSet.getString(6));
				storageModel.setShiftTime(rSet.getDate(7));
				list.add(storageModel);
			}
			sql.closeDB(rSet);
			jString = JSON.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sql.closeDB();
		}
		return jString;
	}
    //调仓
    public String doChangeWareHouse(String quantity,String targetPosition,String remark,String storageId,String account) {
		//对原仓位库存操作
    	String regSql_pre="UPDATE storage SET quantity = quantity - '"+quantity+"' WHERE storage_id ='"+storageId+"'";
    	System.out.println(regSql_pre);
    	DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql_pre);
        if (ret != 0) {
        	String regSql_target = "INSERT INTO storage(goods_record,storage_box,enterprise_record,quantity,create_user,edit_record) VALUES ((SELECT a.goods_record FROM storage a WHERE a.storage_id = '"+storageId+"'),(SELECT b.storage_box_id FROM storage_box b WHERE b.storage_box_code ='"+targetPosition+"'),(SELECT a.enterprise_record FROM storage a WHERE a.storage_id ='"+storageId+"'),'"+quantity+"','"+account+"','"+remark+"')";
            System.out.println(regSql_target);
        	int rs = sql.executeUpdate(regSql_target);
            if (rs != 0) {
				return "调仓成功";
			}
        	sql.closeDB();
        }
        sql.closeDB();
    	return "请核对信息";
	}
//    public static void main(String[] args) {
//		Service service = new Service();
//		System.out.println(service.getEmpInfo("258"));
//	}
}
