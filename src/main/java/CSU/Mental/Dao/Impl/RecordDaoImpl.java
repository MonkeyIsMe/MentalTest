package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.RecordDao;
import CSU.Mental.Model.Problem;
import CSU.Mental.Model.Record;

@Transactional
public class RecordDaoImpl extends HibernateDaoSupport implements RecordDao{

	public int AddRecord(Record record) {
		// TODO Auto-generated method stub
		boolean flag = true;
		int rid = 1;
		try {
			getHibernateTemplate().save(record);
			rid = record.getRecordId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return rid;
	}

	public boolean DeleteRecord(Record record) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(record);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateRecord(Record record) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(record);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Record QueryRecord(int id) {
		// TODO Auto-generated method stub
		Record reuslt = null;
		try {
			reuslt = getHibernateTemplate().get(Record.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reuslt;
	}

	public List<Record> QueryRecordByUserPageSize(final int UserId, final int rows, final int PageSize) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Record>>() {

				public List<Record> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Record where user_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, UserId);
					List<Record> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Record> QueryRecordByPatientPageSize(final int PatientId, final int rows, final int PageSize) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Record>>() {

				public List<Record> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Record where patient_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, PatientId);
					List<Record> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Record> QueryRecordByScalePageSize(final int ScaleId, final int rows, final int PageSize) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Record>>() {

				public List<Record> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Record where scale_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, ScaleId);
					List<Record> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Record> QueryRecordPageSize(final int rows, final int PageSize) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Record>>() {

				public List<Record> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Record";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					List<Record> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountRecord() {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Record as record"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int CountRecorByUserd(int UserId) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Record as record where user_id = '"+ UserId +"'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int CountRecordByPatient(int PatientId) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Record as record where patient_id = '"+ PatientId +"'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int CountRecordByScale(int ScaleId) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Record as record where scale_id = '"+ ScaleId +"'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
