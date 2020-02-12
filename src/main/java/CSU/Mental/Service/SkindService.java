package CSU.Mental.Service;

import java.util.List;

import CSU.Mental.Model.Skind;

public interface SkindService {
	
	public boolean AddSkind(Skind skind);
	
	public boolean DeleteSkind(Skind skind);
	
	public boolean UpdateSkind(Skind skind);
	
	public Skind QuerySkind(int id);
	
	public List<Skind> QuerySkindByFkind(int FkindId);
	
}
