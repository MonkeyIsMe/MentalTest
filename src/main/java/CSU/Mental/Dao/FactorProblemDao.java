package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.FactorProblem;

public interface FactorProblemDao {
	
	public boolean AddFactorProblem(FactorProblem fp);
	
	public boolean UpdateFactorProblem(FactorProblem fp);
	
	public boolean DeleteFactorProblem(FactorProblem fp);
	
	public FactorProblem QueryFactorProblem(int id);
	
	public List<FactorProblem> QueryFactorProblemByProblem(int ProblemId);
	
	public Object AddMutiplyFactorProblem(List<FactorProblem> list);
}
