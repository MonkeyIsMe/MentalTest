package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.User;

public interface UserDao {
	
	public boolean AddUser(User user);
	
	public boolean DeleteUser(User user);
	
	public boolean UpdateUser(User user);
	
	public User QueryUser(int id);
	
	public List<User> QueryUserPageSize(int rows,int PageSize);
	
	public int CountUser();
	
	public List<User> QueryUserByAccount(String UserAccount);
	
}
