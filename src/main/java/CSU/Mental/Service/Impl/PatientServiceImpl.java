package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.PatientDao;
import CSU.Mental.Model.Patient;
import CSU.Mental.Service.PatientService;

@Transactional
public class PatientServiceImpl implements PatientService{

	private PatientDao PatientDao;
	
	public PatientDao getPatientDao() {
		return PatientDao;
	}

	public void setPatientDao(PatientDao patientDao) {
		PatientDao = patientDao;
	}

	public boolean AddPatient(Patient patient) {
		// TODO Auto-generated method stub
		return PatientDao.AddPatient(patient);
	}

	public boolean DeletePatient(Patient patient) {
		// TODO Auto-generated method stub
		return PatientDao.DeletePatient(patient);
	}

	public boolean UpdatePatient(Patient patient) {
		// TODO Auto-generated method stub
		return PatientDao.UpdatePatient(patient);
	}

	public Patient QueryPatient(int id) {
		// TODO Auto-generated method stub
		return PatientDao.QueryPatient(id);
	}

	public int CountPatient() {
		// TODO Auto-generated method stub
		return PatientDao.CountPatient();
	}

	public List<Patient> QueryPatientPageSize(int PageSize, int rows) {
		// TODO Auto-generated method stub
		return PatientDao.QueryPatientPageSize(PageSize, rows);
	}

	public List<Patient> QueryPatientByUserPageSize(int PageSize, int rows, int UserId) {
		// TODO Auto-generated method stub
		return PatientDao.QueryPatientByUserPageSize(PageSize, rows, UserId);
	}

	public int CountPatientByUser(int UserId) {
		// TODO Auto-generated method stub
		return PatientDao.CountPatientByUser(UserId);
	}

	public List<Patient> VaguePatientByNamePageSize(int PageSize, int rows, String name) {
		// TODO Auto-generated method stub
		return PatientDao.VaguePatientByNamePageSize(PageSize, rows, name);
	}

	public int CountVaguePatient(String name) {
		// TODO Auto-generated method stub
		return PatientDao.CountVaguePatient(name);
	}

}
