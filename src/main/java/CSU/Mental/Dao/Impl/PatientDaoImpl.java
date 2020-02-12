package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.PatientDao;
import CSU.Mental.Model.Patient;
import CSU.Mental.Model.Scale;

@Transactional
public class PatientDaoImpl extends HibernateDaoSupport implements PatientDao{

	public boolean AddPatient(Patient patient) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().save(patient);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean DeletePatient(Patient patient) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(patient);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdatePatient(Patient patient) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(patient);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public Patient QueryPatient(int id) {
		// TODO Auto-generated method stub
		Patient result = null;
		try {
			result = getHibernateTemplate().get(Patient.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int CountPatient() {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Patient as patient"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public List<Patient> QueryPatientPageSize(final int PageSize, final int rows) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Patient>>() {

				public List<Patient> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Patient";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					List<Patient> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Patient> QueryPatientByUserPageSize(final int PageSize, final int rows,final int UserId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Patient>>() {

				public List<Patient> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Patient where user_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, UserId);
					List<Patient> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Patient> VaguePatientByNamePageSize(final int PageSize, final int rows,final String name) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Patient>>() {

				public List<Patient> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Patient where user_name like :name";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setString("name", "%"+ name +"%");
					List<Patient> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountPatientByUser(int UserId) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Patient as patient where user_id = '"+ UserId +"'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int CountVaguePatient(String name) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Patient as patient where patient_name like '%"+ name +"%'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
