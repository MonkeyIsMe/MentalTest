package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ChoiceDao;
import CSU.Mental.Model.Choice;
import CSU.Mental.Service.ChoiceService;

@Transactional
public class ChoiceServiceImpl implements ChoiceService{

	private ChoiceDao ChoiceDao;
	
	public ChoiceDao getChoiceDao() {
		return ChoiceDao;
	}

	public void setChoiceDao(ChoiceDao choiceDao) {
		ChoiceDao = choiceDao;
	}

	public boolean AddChoice(Choice choice) {
		// TODO Auto-generated method stub
		return ChoiceDao.AddChoice(choice);
	}

	public boolean UpdateChoice(Choice choice) {
		// TODO Auto-generated method stub
		return ChoiceDao.UpdateChoice(choice);
	}

	public boolean DeleteChoice(Choice choice) {
		// TODO Auto-generated method stub
		return ChoiceDao.DeleteChoice(choice);
	}

	public Choice QueryChoice(int id) {
		// TODO Auto-generated method stub
		return ChoiceDao.QueryChoice(id);
	}

	public List<Choice> QueryChoiceByProblem(int ProblemId) {
		// TODO Auto-generated method stub
		return ChoiceDao.QueryChoiceByProblem(ProblemId);
	}

	public List<Choice> QueryChoiceByTemplate(int TemplateId) {
		// TODO Auto-generated method stub
		return ChoiceDao.QueryChoiceByTemplate(TemplateId);
	}

	public Object DeleteMutiplyChoice(List<Choice> list) {
		// TODO Auto-generated method stub
		return ChoiceDao.DeleteMutiplyChoice(list);
	}

	public Object AddMutiplyChoice(List<Choice> list) {
		// TODO Auto-generated method stub
		return ChoiceDao.AddMutiplyChoice(list);
	}

}
