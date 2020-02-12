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
@Table(name="tab_record")
public class Record implements Serializable{

	@Id
	@Column(name="record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int RecordId;  // 主键
	
	@Column(name="patient_id")
	private int PatientId;  // 病人编号
	
	@Column(name="record_time")
	private String RecordTime;  // 记录时间
	
	@Column(name="user_id")
	private int UserId;  // 用户编号
	
	@Column(name="scale_id")
	private int ScaleId;  // 量表编号
	
	@Column(name="record_info")
	private String RecordInfo;  // 结论信息
	
	@Column(name="record_problem")
	private String RecordProblem;  // 题目信息
	
	@Column(name="record_factor")
	private String RecordFactor;  // 因子信息

	public int getRecordId() {
		return RecordId;
	}

	public void setRecordId(int recordId) {
		RecordId = recordId;
	}

	public int getPatientId() {
		return PatientId;
	}

	public void setPatientId(int patientId) {
		PatientId = patientId;
	}

	public String getRecordTime() {
		return RecordTime;
	}

	public void setRecordTime(String recordTime) {
		RecordTime = recordTime;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getScaleId() {
		return ScaleId;
	}

	public void setScaleId(int scaleId) {
		ScaleId = scaleId;
	}

	public String getRecordInfo() {
		return RecordInfo;
	}

	public void setRecordInfo(String recordInfo) {
		RecordInfo = recordInfo;
	}
	
	public String getRecordProblem() {
		return RecordProblem;
	}

	public void setRecordProblem(String recordProblem) {
		RecordProblem = recordProblem;
	}

	public String getRecordFactor() {
		return RecordFactor;
	}

	public void setRecordFactor(String recordFactor) {
		RecordFactor = recordFactor;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("RecordId", this.RecordId);
		jo.put("PatientId", this.PatientId);
		jo.put("RecordTime", this.RecordTime);
		jo.put("UserId", this.UserId);
		jo.put("RecordInfo", this.RecordInfo);
		jo.put("ScaleId", this.ScaleId);
		jo.put("RecordProblem", this.RecordProblem);
		jo.put("RecordFactor", this.RecordFactor);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
