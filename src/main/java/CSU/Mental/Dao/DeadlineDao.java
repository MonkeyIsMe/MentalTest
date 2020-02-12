package CSU.Mental.Dao;

import CSU.Mental.Model.Deadline;

public interface DeadlineDao {
	
	public boolean AddDeadLine(Deadline deadline);
	
	public boolean UpdateDeadLine(Deadline deadline);
	
	public boolean DeleteDeadLine(Deadline deadline);
	
	public Deadline QueryDeadline(int id);
	
}
