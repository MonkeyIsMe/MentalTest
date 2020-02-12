package CSU.Mental.Dao.Impl;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.HospitalDao;
import CSU.Mental.Model.Hospital;

@Transactional
public class HospitalDaoImpl extends HibernateDaoSupport implements HospitalDao{

	public boolean AddHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		boolean flag = true;
		
		try {
			getHibernateTemplate().save(hospital);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public boolean DeleteHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		boolean flag = true;
		
		try {
			getHibernateTemplate().delete(hospital);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public boolean UpdateHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		boolean flag = true;
		
		try {
			getHibernateTemplate().update(hospital);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public Hospital QueryHospital(int id) {
		// TODO Auto-generated method stub
		Hospital result = null;
		try {
			result = getHibernateTemplate().get(Hospital.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
