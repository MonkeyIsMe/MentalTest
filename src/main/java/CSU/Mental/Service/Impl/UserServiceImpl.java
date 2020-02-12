package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.UserDao;
import CSU.Mental.Model.User;
import CSU.Mental.Service.UserService;

@Transactional
public class UserServiceImpl implements UserService{

	private UserDao UserDao;
	
	public UserDao getUserDao() {
		return UserDao;
	}

	public void setUserDao(UserDao userDao) {
		UserDao = userDao;
	}

	public boolean AddUser(User user) {
		// TODO Auto-generated method stub
		return UserDao.AddUser(user);
	}

	public boolean DeleteUser(User user) {
		// TODO Auto-generated method stub
		return UserDao.DeleteUser(user);
	}

	public boolean UpdateUser(User user) {
		// TODO Auto-generated method stub
		return UserDao.UpdateUser(user);
	}

	public User QueryUser(int id) {
		// TODO Auto-generated method stub
		return UserDao.QueryUser(id);
	}

	public List<User> QueryUserPageSize(int rows, int PageSize) {
		// TODO Auto-generated method stub
		return UserDao.QueryUserPageSize(rows, PageSize);
	}

	public int CountUser() {
		// TODO Auto-generated method stub
		return UserDao.CountUser();
	}

	public User QueryUserByAccount(String UserAccount) {
		// TODO Auto-generated method stub
		List<User> UserList = UserDao.QueryUserByAccount(UserAccount);
		if(UserList.size() == 0) return null;
		return UserList.get(0);
	}

	public boolean QueryUserIsExist(String UserAccount) {
		// TODO Auto-generated method stub
		List<User> UserList = UserDao.QueryUserByAccount(UserAccount);
		if(UserList.size() == 0) return false;
		return true;
	}

}
