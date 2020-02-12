package CSU.Mental.Service;

import java.util.List;

import CSU.Mental.Model.Fkind;

public interface FkindService {
	
	public boolean AddFkind(Fkind fkind);
	
	public boolean UpdateFkind(Fkind fkind);
	
	public boolean DeleteFkind(Fkind fkind);
	
	public Fkind QueryFkind(int id);
	
	public List<Fkind> QueryAllFkind();
	
	public int CountFkind();
	
}
