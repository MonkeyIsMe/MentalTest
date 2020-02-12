package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.FactorDao;
import CSU.Mental.Model.Factor;
import CSU.Mental.Service.FactorService;

@Transactional
public class FactorServiceImpl implements FactorService{

	private FactorDao FactorDao;
	
	public FactorDao getFactorDao() {
		return FactorDao;
	}

	public void setFactorDao(FactorDao factorDao) {
		FactorDao = factorDao;
	}

	public int AddFactor(Factor factor) {
		// TODO Auto-generated method stub
		return FactorDao.AddFactor(factor);
	}

	public boolean DeleteFactor(Factor factor) {
		// TODO Auto-generated method stub
		return FactorDao.DeleteFactor(factor);
	}

	public boolean UpdateFactor(Factor factor) {
		// TODO Auto-generated method stub
		return FactorDao.UpdateFactor(factor);
	}

	public Factor QueryFactor(int id) {
		// TODO Auto-generated method stub
		return FactorDao.QueryFactor(id);
	}

	public List<Factor> QueryFactorByScale(int ScaleId) {
		// TODO Auto-generated method stub
		return FactorDao.QueryFactorByScale(ScaleId);
	}

}
