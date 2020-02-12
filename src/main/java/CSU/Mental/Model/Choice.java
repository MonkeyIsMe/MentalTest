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
@Table(name="tab_choice")
public class Choice implements Serializable{

	@Id
	@Column(name="choice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ChoiceId;  // 主键
	
	@Column(name="choice_info")
	private String ChoiceInfo;  // 内容
	
	@Column(name="choice_sub")
	private String ChoiceSub;  // 是否有子选项，1为有，0为无
	
	@Column(name="problem_id")
	private int ProblemId;  // 题目id
	
	@Column(name="choice_score")
	private int ChoiceScore;  // 得分
	
	@Column(name="choice_type")
	private int ChoiceType;  // 有子选项的类型，0为跳过主选项进入子选项，1为做完主选项做子选项
	
	@Column(name="template_id")
	private int TemplateId;  //模板编号

	public int getChoiceId() {
		return ChoiceId;
	}

	public void setChoiceId(int choiceId) {
		ChoiceId = choiceId;
	}

	public String getChoiceInfo() {
		return ChoiceInfo;
	}

	public void setChoiceInfo(String choiceInfo) {
		ChoiceInfo = choiceInfo;
	}

	public String getChoiceSub() {
		return ChoiceSub;
	}

	public void setChoiceSub(String choiceSub) {
		ChoiceSub = choiceSub;
	}

	public int getProblemId() {
		return ProblemId;
	}

	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}

	public int getChoiceScore() {
		return ChoiceScore;
	}

	public void setChoiceScore(int choiceScore) {
		ChoiceScore = choiceScore;
	}
	
	public int getChoiceType() {
		return ChoiceType;
	}

	public void setChoiceType(int choiceType) {
		ChoiceType = choiceType;
	}

	public int getTemplateId() {
		return TemplateId;
	}

	public void setTemplateId(int templateId) {
		TemplateId = templateId;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("ChoiceId", this.ChoiceId);
		jo.put("ChoiceInfo", this.ChoiceInfo);
		jo.put("ChoiceSub", this.ChoiceSub);
		jo.put("ProblemId", this.ProblemId);
		jo.put("ChoiceScore", this.ChoiceScore);
		jo.put("ChoiceType", this.ChoiceType);
		jo.put("TemplateId", this.TemplateId);
		return jo;
	}

	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
