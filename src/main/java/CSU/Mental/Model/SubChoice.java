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
@Table(name="tab_subchoice")
public class SubChoice implements Serializable{

	@Id
	@Column(name="subchoice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SubChoiceId;  // 主键
	
	@Column(name="subchoice_info")
	private String SubChoiceInfo;  // 内容
	
	@Column(name="choice_id")
	private int ChoiceId;  // 题目id
	
	@Column(name="subchoice_score")
	private int SubChoiceScore;  // 得分

	public int getSubChoiceId() {
		return SubChoiceId;
	}

	public void setSubChoiceId(int subChoiceId) {
		SubChoiceId = subChoiceId;
	}

	public String getSubChoiceInfo() {
		return SubChoiceInfo;
	}

	public void setSubChoiceInfo(String subChoiceInfo) {
		SubChoiceInfo = subChoiceInfo;
	}

	public int getChoiceId() {
		return ChoiceId;
	}

	public void setChoiceId(int choiceId) {
		ChoiceId = choiceId;
	}

	public int getSubChoiceScore() {
		return SubChoiceScore;
	}

	public void setSubChoiceScore(int subChoiceScore) {
		SubChoiceScore = subChoiceScore;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("ChoiceId", this.ChoiceId);
		jo.put("SubChoiceInfo", this.SubChoiceInfo);
		jo.put("SubChoiceId", this.SubChoiceId);
		jo.put("SubChoiceScore", this.SubChoiceScore);
		return jo;
	}

	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
