package CSU.Mental.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_factorproblem")
public class FactorProblem {
	
	@Id
	@Column(name="fp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FactorProblemId;  // 主键
	
	@Column(name="factor_id")
	private int FactorId;  // 因子编号
	
	@Column(name="problem_id")
	private int ProblemId;  // 问题主键
	
	@Column(name="problem_number")
	private String ProblemNumber;  // 问题编号

	public int getFactorProblemId() {
		return FactorProblemId;
	}

	public void setFactorProblemId(int factorProblemId) {
		FactorProblemId = factorProblemId;
	}

	public int getFactorId() {
		return FactorId;
	}

	public void setFactorId(int factorId) {
		FactorId = factorId;
	}

	public int getProblemId() {
		return ProblemId;
	}

	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}

	public String getProblemNumber() {
		return ProblemNumber;
	}

	public void setProblemNumber(String problemNumber) {
		ProblemNumber = problemNumber;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("FactorId", this.FactorId);
		jo.put("ProblemId", this.ProblemId);
		jo.put("FactorProblemId", this.FactorProblemId);
		jo.put("ProblemNumber", this.ProblemNumber);
		return jo;
	}

	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
