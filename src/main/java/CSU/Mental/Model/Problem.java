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
@Table(name="tab_problem")
public class Problem implements Serializable{

	@Id
	@Column(name="problem_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProblemId;  // 主键
	
	@Column(name="problem_name")
	private String ProblemName;  // 题目
	
	@Column(name="problem_type")
	private String ProblemType;  // 类型，0代表填空，1代表单选，2代表多选
	
	@Column(name="scale_id")
	private int ScaleId;  // 量表编号
	
	@Column(name="template_id")
	private int TemplateId;  //模板编号
	
	@Column(name="problem_flag")
	private int ProblemFlag;  //是否必做，0代表非必做，1代表必做

	@Column(name="problem_number")
	private String ProblemNumber;  //序号
	

	public int getProblemId() {
		return ProblemId;
	}

	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}

	public String getProblemName() {
		return ProblemName;
	}

	public void setProblemName(String problemName) {
		ProblemName = problemName;
	}

	public String getProblemType() {
		return ProblemType;
	}

	public void setProblemType(String problemType) {
		ProblemType = problemType;
	}

	public int getScaleId() {
		return ScaleId;
	}

	public void setScaleId(int scaleId) {
		ScaleId = scaleId;
	}
	
	public int getTemplateId() {
		return TemplateId;
	}

	public void setTemplateId(int templateId) {
		TemplateId = templateId;
	}

	public String getProblemNumber() {
		return ProblemNumber;
	}

	public void setProblemNumber(String problemNumber) {
		ProblemNumber = problemNumber;
	}

	public int getProblemFlag() {
		return ProblemFlag;
	}

	public void setProblemFlag(int problemFlag) {
		ProblemFlag = problemFlag;
	}


	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("ProblemId", this.ProblemId);
		jo.put("ProblemName", this.ProblemName);
		jo.put("ProblemType", this.ProblemType);
		jo.put("ScaleId", this.ScaleId);
		jo.put("TemplateId", this.TemplateId);
		jo.put("ProblemFlag", this.ProblemFlag);
		jo.put("ProblemNumber", this.ProblemNumber);

		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
