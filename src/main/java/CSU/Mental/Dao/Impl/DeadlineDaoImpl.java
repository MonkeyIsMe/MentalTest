package CSU.Mental.Dao.Impl;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.DeadlineDao;
import CSU.Mental.Model.Deadline;

@Transactional
public class DeadlineDaoImpl extends HibernateDaoSupport implements DeadlineDao{

	public boolean AddDeadLine(Deadline deadline) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().save(deadline);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return false;
	}

	public boolean UpdateDeadLine(Deadline deadline) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(deadline);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return false;
	}

	public boolean DeleteDeadLine(Deadline deadline) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(deadline);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return false;
	}

	public Deadline QueryDeadline(int id) {
		// TODO Auto-generated method stub
		Deadline reuslt = null;
		try {
			reuslt = getHibernateTemplate().get(Deadline.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reuslt;
	}

}
