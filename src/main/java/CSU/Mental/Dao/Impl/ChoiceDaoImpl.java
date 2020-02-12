package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ChoiceDao;
import CSU.Mental.Model.Choice;

@Transactional
public class ChoiceDaoImpl extends HibernateDaoSupport implements ChoiceDao{

	public boolean AddChoice(Choice choice) {
		// TODO Auto-generated method stub
		boolean flag = true;
		
		try {
			getHibernateTemplate().save(choice);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public boolean UpdateChoice(Choice choice) {
		// TODO Auto-generated method stub
		boolean flag = true;
		
		try {
			getHibernateTemplate().update(choice);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public boolean DeleteChoice(Choice choice) {
		// TODO Auto-generated method stub
		boolean flag = true;
		
		try {
			getHibernateTemplate().delete(choice);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public Choice QueryChoice(int id) {
		// TODO Auto-generated method stub
		Choice result = null;
		
		try {
			result = getHibernateTemplate().get(Choice.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Choice> QueryChoiceByProblem(final int ProblemId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Choice>>() {

				public List<Choice> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Choice where problem_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, ProblemId);
					List<Choice> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Choice> QueryChoiceByTemplate(final int TemplateId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Choice>>() {

				public List<Choice> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Choice where template_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, TemplateId);
					List<Choice> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object DeleteMutiplyChoice(final List<Choice> list) {
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

	public Object AddMutiplyChoice(final List<Choice> list) {
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
