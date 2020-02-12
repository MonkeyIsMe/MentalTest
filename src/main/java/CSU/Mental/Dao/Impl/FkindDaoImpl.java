package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.FkindDao;
import CSU.Mental.Model.FactorProblem;
import CSU.Mental.Model.Fkind;

@Transactional
public class FkindDaoImpl extends HibernateDaoSupport implements FkindDao{

	public boolean AddFkind(Fkind fkind) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().save(fkind);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateFkind(Fkind fkind) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(fkind);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean DeleteFkind(Fkind fkind) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(fkind);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Fkind QueryFkind(int id) {
		// TODO Auto-generated method stub
		Fkind result = null;
		try {
			result = getHibernateTemplate().get(Fkind.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Fkind> QueryAllFkind() {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Fkind>>() {

				public List<Fkind> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Fkind";
					Query query = session.createQuery(hql);
					List<Fkind> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountFkind() {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Fkind as fkind"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
