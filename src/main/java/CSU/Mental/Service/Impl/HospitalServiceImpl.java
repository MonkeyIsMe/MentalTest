package CSU.Mental.Service.Impl;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.HospitalDao;
import CSU.Mental.Model.Hospital;
import CSU.Mental.Service.HospitalService;

@Transactional
public class HospitalServiceImpl implements HospitalService{

	private HospitalDao HospitalDao;
	
	public HospitalDao getHospitalDao() {
		return HospitalDao;
	}

	public void setHospitalDao(HospitalDao hospitalDao) {
		HospitalDao = hospitalDao;
	}

	public boolean AddHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		return HospitalDao.AddHospital(hospital);
	}

	public boolean DeleteHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		return HospitalDao.DeleteHospital(hospital);
	}

	public boolean UpdateHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		return HospitalDao.UpdateHospital(hospital);
	}

	public Hospital QueryHospital(int id) {
		// TODO Auto-generated method stub
		return HospitalDao.QueryHospital(id);
	}

}
