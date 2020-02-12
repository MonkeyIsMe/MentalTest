package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.SubChoiceDao;
import CSU.Mental.Model.Skind;
import CSU.Mental.Model.SubChoice;

@Transactional
public class SubChoiceDaoImpl extends HibernateDaoSupport implements SubChoiceDao{

	public boolean AddSubChoice(SubChoice choice) {
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

	public boolean DeleteSubChoice(SubChoice choice) {
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

	public boolean UpdateSubChoice(SubChoice choice) {
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
	
	public SubChoice QuerySubChoice(int id) {
		// TODO Auto-generated method stub
		SubChoice result = null;
		try {
			result = getHibernateTemplate().get(SubChoice.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<SubChoice> QueryChoiceByChoice(final int ChoiceId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<SubChoice>>() {

				public List<SubChoice> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from SubChoice where choice_id = ?";
					Query query = session.createQuery(hql);
					query.setParameter(0, ChoiceId);
					List<SubChoice> list = query.list();
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
