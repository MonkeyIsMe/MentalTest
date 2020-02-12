package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.SkindDao;
import CSU.Mental.Model.Skind;
import CSU.Mental.Service.SkindService;

@Transactional
public class SkindServiceImpl implements SkindService{

	private SkindDao SkindDao;
	
	
	public SkindDao getSkindDao() {
		return SkindDao;
	}

	public void setSkindDao(SkindDao skindDao) {
		SkindDao = skindDao;
	}

	public boolean AddSkind(Skind skind) {
		// TODO Auto-generated method stub
		return SkindDao.AddSkind(skind);
	}

	public boolean DeleteSkind(Skind skind) {
		// TODO Auto-generated method stub
		return SkindDao.DeleteSkind(skind);
	}

	public boolean UpdateSkind(Skind skind) {
		// TODO Auto-generated method stub
		return SkindDao.UpdateSkind(skind);
	}

	public Skind QuerySkind(int id) {
		// TODO Auto-generated method stub
		return SkindDao.QuerySkind(id);
	}

	public List<Skind> QuerySkindByFkind(int FkindId) {
		// TODO Auto-generated method stub
		return SkindDao.QuerySkindByFkind(FkindId);
	}

}
