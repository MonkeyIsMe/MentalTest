package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.FactorDao;
import CSU.Mental.Model.Factor;

@Transactional
public class FactorDaoImpl extends HibernateDaoSupport implements FactorDao{

	public int AddFactor(Factor factor) {
		// TODO Auto-generated method stub
		int id = 0;
		try {
			getHibernateTemplate().save(factor);
			id = factor.getFactorId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//flag = false;
		}
		return id;
	}

	public boolean DeleteFactor(Factor factor) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(factor);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateFactor(Factor factor) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(factor);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Factor QueryFactor(int id) {
		// TODO Auto-generated method stub
		Factor result = null;
		try {
			result = getHibernateTemplate().get(Factor.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Factor> QueryFactorByScale(final int ScaleId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Factor>>() {

				public List<Factor> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Factor where scale_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, ScaleId);
					List<Factor> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object DeleteMutiplyFactor(final List<Factor> list) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < list.size(); i ++) {
					session.delete(list.get(i));
                    if (i % 50 == 0) {  
                        session.flush();  
                        session.clear();  
                    }  
				}
				return null;
			}
			
		});
	}

}
