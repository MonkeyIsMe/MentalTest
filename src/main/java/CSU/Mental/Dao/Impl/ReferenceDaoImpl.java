package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ReferenceDao;
import CSU.Mental.Model.Problem;
import CSU.Mental.Model.Reference;

@Transactional
public class ReferenceDaoImpl extends HibernateDaoSupport implements ReferenceDao{

	public boolean AddReference(Reference refer) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().save(refer);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateReference(Reference refer) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(refer);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean DeleteReference(Reference refer) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(refer);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Reference QueryReference(int id) {
		// TODO Auto-generated method stub
		Reference result = null;
		try {
			result = getHibernateTemplate().get(Reference.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Reference> QueryReferenceByFactor(final int FactorId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Reference>>() {

				public List<Reference> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Reference where factor_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, FactorId);
					List<Reference> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object DeleteMutiplyReference(final List<Reference> list) {
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
