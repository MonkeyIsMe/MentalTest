package CSU.Mental.Service.Impl;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.DeadlineDao;
import CSU.Mental.Model.Deadline;
import CSU.Mental.Service.DeadlineService;

@Transactional
public class DeadlineServiceImpl implements DeadlineService{

	private DeadlineDao DeadlineDao;
	
	public DeadlineDao getDeadlineDao() {
		return DeadlineDao;
	}

	public void setDeadlineDao(DeadlineDao deadlineDao) {
		DeadlineDao = deadlineDao;
	}

	public boolean AddDeadLine(Deadline deadline) {
		// TODO Auto-generated method stub
		return DeadlineDao.AddDeadLine(deadline);
	}

	public boolean UpdateDeadLine(Deadline deadline) {
		// TODO Auto-generated method stub
		return DeadlineDao.UpdateDeadLine(deadline);
	}

	public boolean DeleteDeadLine(Deadline deadline) {
		// TODO Auto-generated method stub
		return DeadlineDao.DeleteDeadLine(deadline);
	}

	public Deadline QueryDeadline(int id) {
		// TODO Auto-generated method stub
		return DeadlineDao.QueryDeadline(id);
	}

}
