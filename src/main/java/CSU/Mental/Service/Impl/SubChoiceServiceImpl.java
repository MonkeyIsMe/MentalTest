package CSU.Mental.Service.Impl;

import java.util.List;

import CSU.Mental.Dao.SubChoiceDao;
import CSU.Mental.Model.SubChoice;
import CSU.Mental.Service.SubChoiceService;

public class SubChoiceServiceImpl implements SubChoiceService{

	private SubChoiceDao SubChoiceDao;
	
	public SubChoiceDao getSubChoiceDao() {
		return SubChoiceDao;
	}

	public void setSubChoiceDao(SubChoiceDao subChoiceDao) {
		SubChoiceDao = subChoiceDao;
	}

	public boolean AddSubChoice(SubChoice choice) {
		// TODO Auto-generated method stub
		return SubChoiceDao.AddSubChoice(choice);
	}

	public boolean DeleteSubChoice(SubChoice choice) {
		// TODO Auto-generated method stub
		return SubChoiceDao.DeleteSubChoice(choice);
	}

	public boolean UpdateSubChoice(SubChoice choice) {
		// TODO Auto-generated method stub
		return SubChoiceDao.UpdateSubChoice(choice);
	}

	public SubChoice QuerySubChoice(int id) {
		// TODO Auto-generated method stub
		return SubChoiceDao.QuerySubChoice(id);
	}

	public List<SubChoice> QueryChoiceByChoice(int ChoiceId) {
		// TODO Auto-generated method stub
		return SubChoiceDao.QueryChoiceByChoice(ChoiceId);
	}

}
