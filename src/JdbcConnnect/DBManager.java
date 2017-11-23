package JdbcConnnect;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBManager {
	private String driverclass;
	private String url;
	private String username;
	private String password;
	
	// 静态成员，支持单态模式
    private static DBManager per = null;
    private Connection conn = null;
    private Statement stmt = null;

	
	// 单态模式-懒汉模式
    private DBManager() {
    }

    public static DBManager createInstance() {
        if (per == null) {
            per = new DBManager();
            per.initDB();
        }
        return per;
    }

    // 获取配置文件 ,加载驱动
    public void initDB() {
        try {
        	InputStream inputStream=getClass().getClassLoader().getResourceAsStream("jdbc.properties");
    		Properties properties=new Properties();
    		properties.load(inputStream);
    		
    		driverclass=properties.getProperty("driver");
    		url=properties.getProperty("Url");
    		username=properties.getProperty("user");
    		password=properties.getProperty("password");
            Class.forName(driverclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 // 连接数据库，获取句柄+对象
    public void connectDB() {
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn!=null) {
        	System.out.println("SqlManager:Connect to database successful.");
		}
        else {
        	System.out.println("SqlManager:Connect to database unsuccessful.");
		}
    }
 // 关闭数据库 关闭对象，释放句柄
    public void closeDB() {
        System.out.println("Close connection to database..");
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successful");
    }
    public void closeDB(ResultSet rs) {
        System.out.println("Close connection to database..");
        try {
        	rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successful");
    }
 // 查询
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
 // 增添/删除/修改
    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    //测试连接
//    public static void main(String[] args) {
//		DBManager aDbManager=DBManager.createInstance();
//		aDbManager.connectDB();
//		
//	}
}
