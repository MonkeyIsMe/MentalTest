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
@Table(name="tab_patient")
public class Patient implements Serializable{
	
	@Id
	@Column(name="patient_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int PatientId;  // 主键
	
	@Column(name="patient_name")
	private String PatientName;  // 病人名字
	
	@Column(name="patient_birthday")
	private String PatientBirthday;  // 出生日期
	
	@Column(name="patient_gender")
	private String PatientGender;  // 性别
	
	@Column(name="patient_identity")
	private String PatientIndentity;  // 身份证号
	
	@Column(name="patient_number")
	private String PatientNumber;  // 住院号/门诊号
	
	@Column(name="patient_nation")
	private String PatientNation;  // 民族
	
	@Column(name="patient_place")
	private String PatientPlace;  // 居住地址
	
	@Column(name="user_id")
	private int UserId;  // 用户编号

	public int getPatientId() {
		return PatientId;
	}

	public void setPatientId(int patientId) {
		PatientId = patientId;
	}

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public String getPatientBirthday() {
		return PatientBirthday;
	}

	public void setPatientBirthday(String patientBirthday) {
		PatientBirthday = patientBirthday;
	}

	public String getPatientGender() {
		return PatientGender;
	}

	public void setPatientGender(String patientGender) {
		PatientGender = patientGender;
	}

	public String getPatientIndentity() {
		return PatientIndentity;
	}

	public void setPatientIndentity(String patientIndentity) {
		PatientIndentity = patientIndentity;
	}

	public String getPatientNumber() {
		return PatientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		PatientNumber = patientNumber;
	}

	public String getPatientNation() {
		return PatientNation;
	}

	public void setPatientNation(String patientNation) {
		PatientNation = patientNation;
	}

	public String getPatientPlace() {
		return PatientPlace;
	}

	public void setPatientPlace(String patientPlace) {
		PatientPlace = patientPlace;
	}
	
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("PatientId", this.PatientId);
		jo.put("PatientName", this.PatientName);
		jo.put("PatientBirthday", this.PatientBirthday);
		jo.put("PatientGender", this.PatientGender);
		jo.put("PatientIndentity", this.PatientIndentity);
		jo.put("PatientNumber", this.PatientNumber);
		jo.put("PatientNation", this.PatientNation);
		jo.put("PatientPlace", this.PatientPlace);
		jo.put("UserId", this.UserId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
