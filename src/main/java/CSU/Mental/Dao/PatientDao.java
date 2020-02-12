package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Patient;

public interface PatientDao {
	
	public boolean AddPatient(Patient patient);
	
	public boolean DeletePatient(Patient patient);
	
	public boolean UpdatePatient(Patient patient);
	
	public Patient QueryPatient(int id);
	
	public int CountPatient();
	
	public List<Patient> QueryPatientByPageSize(int PageSize,int rows);
	
}
