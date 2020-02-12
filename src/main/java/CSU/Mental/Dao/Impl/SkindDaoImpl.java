package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.SkindDao;
import CSU.Mental.Model.Reference;
import CSU.Mental.Model.Skind;

@Transactional
public class SkindDaoImpl extends HibernateDaoSupport implements SkindDao{

	public boolean AddSkind(Skind skind) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().save(skind);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean DeleteSkind(Skind skind) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(skind);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateSkind(Skind skind) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(skind);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Skind QuerySkind(int id) {
		// TODO Auto-generated method stub
		Skind result = null;
		try {
			result = getHibernateTemplate().get(Skind.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Skind> QuerySkindByFkind(final int FkindId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Skind>>() {

				public List<Skind> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Skind where fk_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, FkindId);
					List<Skind> list = query.list();
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
