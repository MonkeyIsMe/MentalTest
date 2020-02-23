package CSU.Mental.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ProblemDao;
import CSU.Mental.Model.Problem;
import CSU.Mental.Model.Scale;

@Transactional
public class ProblemDaoImpl extends HibernateDaoSupport implements ProblemDao{

	public int AddProblem(Problem problem) {
		// TODO Auto-generated method stub
		int pid = 0;
		try {
			getHibernateTemplate().save(problem);
			pid = problem.getProblemId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pid;
	}

	public boolean DeleteProblem(Problem problem) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(problem);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateProblem(Problem problem) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(problem);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Problem QueryProblem(int id) {
		// TODO Auto-generated method stub
		Problem result = null;
		try {
			result = getHibernateTemplate().get(Problem.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Problem> QueryProblemByScale(final int ScaleId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Problem>>() {

				public List<Problem> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Problem where scale_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, ScaleId);
					List<Problem> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountProblemByScale(int ScaleId) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Problem as problem where scale_id = '"+ ScaleId +"'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public List<Problem> QueryProblemByScalePageSize(final int ScaleId, final int PageSize, final int rows) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Problem>>() {

				public List<Problem> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Problem where scale_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, ScaleId);
					List<Problem> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Integer> AddMutiplyProblem(final List<Problem> list) {
		// TODO Auto-generated method stub
		final List<Integer> in_list = new ArrayList<Integer>();
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < list.size(); i ++) {
					session.save(list.get(i));
					int pid = list.get(i).getProblemId();
					in_list.add(pid);
                    if (i % 50 == 0) {  
                        session.flush();  
                        session.clear();  
                    }  
				}
				return null;
			}
			
		});
		return in_list;
	}

	public List<Object[]> QuerySingleProblemByScale(final int ScaleId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {

			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "select ProblemId,ProblemName,ProblemNumber from Problem where scale_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ScaleId);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}

	public Object DeleteMutiplyProblem(final List<Problem> list) {
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

	public Object UpdateMutiplyProblem(final List<Problem> list) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < list.size(); i ++) {
					session.update(list.get(i));
                    if (i % 50 == 0) {  
                        session.flush();  
                        session.clear();  
                    }  
				}
				return null;
			}
			
		});
	}

	public List<Problem> QueryProblemByTemplate(final int TemplateId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Problem>>() {

				public List<Problem> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Problem where template_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, TemplateId);
					List<Problem> list = query.list();
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
