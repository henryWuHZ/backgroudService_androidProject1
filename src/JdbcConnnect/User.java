package JdbcConnnect;

public class User {
	private String userName;
	private int property;
	private String password;
	private String userId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getProperty() {
		return property;
	}
	public void setProperty(int property) {
		this.property = property;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User(String userName, int property, String password, String userId) {
		super();
		this.userName = userName;
		this.property = property;
		this.password = password;
		this.userId = userId;
	}
	public User() {
		
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", property=" + property + ", password=" + password + ", userId=" + userId
				+ "]";
	}
	
	
}
