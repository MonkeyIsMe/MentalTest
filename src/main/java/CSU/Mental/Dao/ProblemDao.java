package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Problem;

public interface ProblemDao {
	
	public int AddProblem(Problem problem);
	
	public boolean DeleteProblem(Problem problem);
	
	public boolean UpdateProblem(Problem problem);
	
	public Problem QueryProblem(int id);
	
	public List<Problem> QueryProblemByScale(int ScaleId);
	
	public int CountProblemByScale(int ScaleId);
	
	public List<Problem> QueryProblemByScalePageSize(int ScaleId,int PageSize,int rows);
	
	public List<Integer> AddMutiplyProblem(List<Problem> list);
	
}
