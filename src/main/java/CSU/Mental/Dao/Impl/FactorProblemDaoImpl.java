package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.FactorProblemDao;
import CSU.Mental.Model.Factor;
import CSU.Mental.Model.FactorProblem;

@Transactional
public class FactorProblemDaoImpl extends HibernateDaoSupport implements FactorProblemDao{

	public boolean AddFactorProblem(FactorProblem fp) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().save(fp);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateFactorProblem(FactorProblem fp) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(fp);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean DeleteFactorProblem(FactorProblem fp) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(fp);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public FactorProblem QueryFactorProblem(int id) {
		// TODO Auto-generated method stub
		FactorProblem result = null;
		try {
			result = getHibernateTemplate().get(FactorProblem.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<FactorProblem> QueryFactorProblemByProblem(final int ProblemId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<FactorProblem>>() {

				public List<FactorProblem> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from FactorProblem where problem_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, ProblemId);
					List<FactorProblem> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object AddMutiplyFactorProblem(final List<FactorProblem> list) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < list.size(); i ++) {
					session.save(list.get(i));
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
