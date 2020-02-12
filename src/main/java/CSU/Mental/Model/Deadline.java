package CSU.Mental.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_deadline")
public class Deadline implements Serializable{
	
	@Id
	@Column(name="deadline_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int DeadlineId;  // 主键
	
	@Column(name="deadline_time")
	private String DeadlineTime;  // 有效时间

	public int getDeadlineId() {
		return DeadlineId;
	}

	public void setDeadlineId(int deadlineId) {
		DeadlineId = deadlineId;
	}

	public String getDeadlineTime() {
		return DeadlineTime;
	}

	public void setDeadlineTime(String deadlineTime) {
		DeadlineTime = deadlineTime;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("DeadlineId", this.DeadlineId);
		jo.put("DeadlineTime", this.DeadlineTime);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
