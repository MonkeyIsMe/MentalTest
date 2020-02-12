package CSU.Mental.Dao;

import CSU.Mental.Model.Hospital;

public interface HospitalDao {

	public boolean AddHospital(Hospital hospital);
	
	public boolean DeleteHospital(Hospital hospital);
	
	public boolean UpdateHospital(Hospital hospital);
	
	public Hospital QueryHospital(int id);
	
}
