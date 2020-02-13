package CSU.Mental.Service;

import java.util.List;

import CSU.Mental.Model.FactorProblem;

public interface FactorProblemService {
	
	public boolean AddFactorProblem(FactorProblem fp);
	
	public boolean UpdateFactorProblem(FactorProblem fp);
	
	public boolean DeleteFactorProblem(FactorProblem fp);
	
	public FactorProblem QueryFactorProblem(int id);
	
	public List<FactorProblem> QueryFactorProblemByProblem(int ProblemId);
	
	public List<FactorProblem> QueryFactorProblemByFactor(int FactorId);
	
	public Object AddMutiplyFactorProblem(List<FactorProblem> list);
	
	public Object DeleteMutiplyFactorProblem(List<FactorProblem> list);
	
}
