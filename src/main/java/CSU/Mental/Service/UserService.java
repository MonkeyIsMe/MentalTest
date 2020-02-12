package CSU.Mental.Service;

import java.util.List;

import CSU.Mental.Model.User;

public interface UserService {
	
	public boolean AddUser(User user);
	
	public boolean DeleteUser(User user);
	
	public boolean UpdateUser(User user);
	
	public User QueryUser(int id);
	
	public List<User> QueryUserPageSize(int rows,int PageSize);
	
	public int CountUser();
	
	public User QueryUserByAccount(String UserAccount);
	
	public boolean QueryUserIsExist(String UserAccount);
	
}
