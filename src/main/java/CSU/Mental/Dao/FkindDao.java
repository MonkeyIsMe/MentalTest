package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Fkind;

public interface FkindDao {
	
	public boolean AddFkind(Fkind fkind);
	
	public boolean UpdateFkind(Fkind fkind);
	
	public boolean DeleteFkind(Fkind fkind);
	
	public Fkind QueryFkind(int id);
	
	public List<Fkind> QueryAllFkind();
	
	public int CountFkind();
	
}
