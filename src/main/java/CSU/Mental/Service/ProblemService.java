package CSU.Mental.Service;

import java.util.List;

import CSU.Mental.Model.Problem;

public interface ProblemService {
	
	public int AddProblem(Problem problem);
	
	public boolean DeleteProblem(Problem problem);
	
	public boolean UpdateProblem(Problem problem);
	
	public Problem QueryProblem(int id);
	
	public List<Problem> QueryProblemByScale(int ScaleId);
	
	public int CountProblemByScale(int ScaleId);
	
	public List<Problem> QueryProblemByScalePageSize(int ScaleId,int PageSize,int rows);
	
	public List<Integer> AddMutiplyProblem(List<Problem> list);
	
	public List<Object[]> QuerySingleProblemByScale(int ScaleId);
	
	public Object DeleteMutiplyProblem(List<Problem> list);
	
	public Object UpdateMutiplyProblem(List<Problem> list);
	
	public List<Problem> QueryProblemByTemplate(int TemplateId);
	
}
