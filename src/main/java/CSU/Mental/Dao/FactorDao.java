package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Factor;

public interface FactorDao {

	public int AddFactor(Factor factor);
	
	public boolean DeleteFactor(Factor factor);
	
	public boolean UpdateFactor(Factor factor);
	
	public Factor QueryFactor(int id);
	
	public List<Factor> QueryFactorByScale(int ScaleId);
	
	public Object DeleteMutiplyFactor(List<Factor> list);
	
}
