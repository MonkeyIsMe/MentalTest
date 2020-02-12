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
@Table(name="tab_reference")
public class Reference implements Serializable{

	@Id
	@Column(name="reference_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ReferenceId;  // 主键
	
	@Column(name="reference_bscore")
	private int ReferenceBeginScore;  // 起始分数
	
	@Column(name="reference_escore")
	private int ReferenceEndScore;  // 结束分数
	
	@Column(name="reference_sex")
	private String ReferenceSex;  // 性别
	
	@Column(name="reference_bage")
	private int ReferenceBeginAge;  // 起始年龄
	
	@Column(name="reference_eage")
	private int ReferenceEndAge;  // 结束年龄
	
	@Column(name="reference_suggestion")
	private String ReferenceSuggestion;  // 建议
	
	@Column(name="factor_id")
	private int FactorId;  // 因素编号

	public int getReferenceId() {
		return ReferenceId;
	}

	public void setReferenceId(int referenceId) {
		ReferenceId = referenceId;
	}

	public int getReferenceBeginScore() {
		return ReferenceBeginScore;
	}

	public void setReferenceBeginScore(int referenceBeginScore) {
		ReferenceBeginScore = referenceBeginScore;
	}

	public int getReferenceEndScore() {
		return ReferenceEndScore;
	}

	public void setReferenceEndScore(int referenceEndScore) {
		ReferenceEndScore = referenceEndScore;
	}

	public String getReferenceSex() {
		return ReferenceSex;
	}

	public void setReferenceSex(String referenceSex) {
		ReferenceSex = referenceSex;
	}

	public int getReferenceBeginAge() {
		return ReferenceBeginAge;
	}

	public void setReferenceBeginAge(int referenceBeginAge) {
		ReferenceBeginAge = referenceBeginAge;
	}

	public int getReferenceEndAge() {
		return ReferenceEndAge;
	}

	public void setReferenceEndAge(int referenceEndAge) {
		ReferenceEndAge = referenceEndAge;
	}

	public String getReferenceSuggestion() {
		return ReferenceSuggestion;
	}

	public void setReferenceSuggestion(String referenceSuggestion) {
		ReferenceSuggestion = referenceSuggestion;
	}

	public int getFactorId() {
		return FactorId;
	}

	public void setFactorId(int factorId) {
		FactorId = factorId;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("ReferenceId", this.ReferenceId);
		jo.put("ReferenceBeginScore", this.ReferenceBeginScore);
		jo.put("ReferenceEndScore", this.ReferenceEndScore);
		jo.put("ReferenceSex", this.ReferenceSex);
		jo.put("ReferenceBeginAge", this.ReferenceBeginAge);
		jo.put("ReferenceEndAge", this.ReferenceEndAge);
		jo.put("ReferenceSuggestion", this.ReferenceSuggestion);
		jo.put("FactorId", this.FactorId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
