package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Patient;

public interface PatientDao {
	
	public boolean AddPatient(Patient patient);
	
	public boolean DeletePatient(Patient patient);
	
	public boolean UpdatePatient(Patient patient);
	
	public Patient QueryPatient(int id);
	
	public int CountPatient();
	
	public List<Patient> QueryPatientPageSize(int PageSize,int rows);
	
	public List<Patient> QueryPatientByUserPageSize(int PageSize,int rows,int UserId);
	
	public int CountPatientByUser(int UserId);
	
	public int CountVaguePatient(String name);
	
	public List<Patient> VaguePatientByNamePageSize(int PageSize,int rows,String name);
	
}
