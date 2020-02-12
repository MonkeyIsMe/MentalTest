package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.FactorProblemDao;
import CSU.Mental.Model.FactorProblem;
import CSU.Mental.Service.FactorProblemService;

@Transactional
public class FactorProblemServiceImpl implements FactorProblemService{

	private FactorProblemDao FactorProblemDao;
	
	public FactorProblemDao getFactorProblemDao() {
		return FactorProblemDao;
	}

	public void setFactorProblemDao(FactorProblemDao factorProblemDao) {
		FactorProblemDao = factorProblemDao;
	}

	public boolean AddFactorProblem(FactorProblem fp) {
		// TODO Auto-generated method stub
		return FactorProblemDao.AddFactorProblem(fp);
	}

	public boolean UpdateFactorProblem(FactorProblem fp) {
		// TODO Auto-generated method stub
		return FactorProblemDao.UpdateFactorProblem(fp);
	}

	public boolean DeleteFactorProblem(FactorProblem fp) {
		// TODO Auto-generated method stub
		return FactorProblemDao.DeleteFactorProblem(fp);
	}

	public FactorProblem QueryFactorProblem(int id) {
		// TODO Auto-generated method stub
		return FactorProblemDao.QueryFactorProblem(id);
	}

	public List<FactorProblem> QueryFactorProblemByProblem(int ProblemId) {
		// TODO Auto-generated method stub
		return FactorProblemDao.QueryFactorProblemByProblem(ProblemId);
	}

	public Object AddMutiplyFactorProblem(List<FactorProblem> list) {
		// TODO Auto-generated method stub
		return FactorProblemDao.AddMutiplyFactorProblem(list);
	}

}
