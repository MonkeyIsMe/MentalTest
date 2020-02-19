package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ProblemDao;
import CSU.Mental.Model.Problem;
import CSU.Mental.Service.ProblemService;

@Transactional
public class ProblemServiceImpl implements ProblemService{

	private ProblemDao ProblemDao;
	
	public ProblemDao getProblemDao() {
		return ProblemDao;
	}

	public void setProblemDao(ProblemDao problemDao) {
		ProblemDao = problemDao;
	}

	public int AddProblem(Problem problem) {
		// TODO Auto-generated method stub
		return ProblemDao.AddProblem(problem);
	}

	public boolean DeleteProblem(Problem problem) {
		// TODO Auto-generated method stub
		return ProblemDao.DeleteProblem(problem);
	}

	public boolean UpdateProblem(Problem problem) {
		// TODO Auto-generated method stub
		return ProblemDao.UpdateProblem(problem);
	}

	public Problem QueryProblem(int id) {
		// TODO Auto-generated method stub
		return ProblemDao.QueryProblem(id);
	}

	public List<Problem> QueryProblemByScale(int ScaleId) {
		// TODO Auto-generated method stub
		return ProblemDao.QueryProblemByScale(ScaleId);
	}

	public int CountProblemByScale(int ScaleId) {
		// TODO Auto-generated method stub
		return ProblemDao.CountProblemByScale(ScaleId);
	}

	public List<Problem> QueryProblemByScalePageSize(int ScaleId, int PageSize, int rows) {
		// TODO Auto-generated method stub
		return ProblemDao.QueryProblemByScalePageSize(ScaleId, PageSize, rows);
	}

	public List<Integer> AddMutiplyProblem(List<Problem> list) {
		// TODO Auto-generated method stub
		return ProblemDao.AddMutiplyProblem(list);
	}

	public List<Object[]> QuerySingleProblemByScale(int ScaleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object DeleteMutiplyProblem(List<Problem> list) {
		// TODO Auto-generated method stub
		return ProblemDao.DeleteMutiplyProblem(list);
	}

}
