package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.UserDao;
import CSU.Mental.Model.Record;
import CSU.Mental.Model.User;

@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	public boolean AddUser(User user) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().save(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean DeleteUser(User user) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateUser(User user) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public User QueryUser(int id) {
		// TODO Auto-generated method stub
		User result = null;
		try {
			result = getHibernateTemplate().get(User.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<User> QueryUserPageSize(final int rows, final int PageSize) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {

				public List<User> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from User";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					List<User> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountUser() {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from User as user"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public List<User> QueryUserByAccount(final String UserAccount) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {

				public List<User> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from User where user_account = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, UserAccount);
					List<User> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
