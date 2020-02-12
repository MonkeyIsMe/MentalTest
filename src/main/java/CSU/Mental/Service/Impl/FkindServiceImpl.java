package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.FkindDao;
import CSU.Mental.Model.Fkind;
import CSU.Mental.Service.FkindService;

@Transactional
public class FkindServiceImpl implements FkindService{

	private FkindDao FkindDao;
	
	public FkindDao getFkindDao() {
		return FkindDao;
	}

	public void setFkindDao(FkindDao fkindDao) {
		FkindDao = fkindDao;
	}

	public boolean AddFkind(Fkind fkind) {
		// TODO Auto-generated method stub
		return FkindDao.AddFkind(fkind);
	}

	public boolean UpdateFkind(Fkind fkind) {
		// TODO Auto-generated method stub
		return FkindDao.UpdateFkind(fkind);
	}

	public boolean DeleteFkind(Fkind fkind) {
		// TODO Auto-generated method stub
		return FkindDao.DeleteFkind(fkind);
	}

	public Fkind QueryFkind(int id) {
		// TODO Auto-generated method stub
		return FkindDao.QueryFkind(id);
	}

	public List<Fkind> QueryAllFkind() {
		// TODO Auto-generated method stub
		return FkindDao.QueryAllFkind();
	}

	public int CountFkind() {
		// TODO Auto-generated method stub
		return 0;
	}

}
