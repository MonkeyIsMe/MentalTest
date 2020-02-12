package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Choice;

public interface ChoiceDao {
	
	public boolean AddChoice(Choice choice);
	
	public boolean UpdateChoice(Choice choice);
	
	public boolean DeleteChoice(Choice choice);
	
	public Choice QueryChoice(int id);
	
	public List<Choice> QueryChoiceByProblem(int ProblemId);
	
	public List<Choice> QueryChoiceByTemplate(int TemplateId);
	
	public Object DeleteMutiplyChoice(List<Choice> list);
	
	public Object AddMutiplyChoice(List<Choice> list);
	
	
}
