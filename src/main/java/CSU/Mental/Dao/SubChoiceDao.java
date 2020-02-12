package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.SubChoice;

public interface SubChoiceDao {
	
	public boolean AddSubChoice(SubChoice choice);
	
	public boolean DeleteSubChoice(SubChoice choice);
	
	public boolean UpdateSubChoice(SubChoice choice);
	
	public SubChoice QuerySubChoice(int id);
	
	public List<SubChoice> QueryChoiceByChoice(int ChoiceId);
	
}
