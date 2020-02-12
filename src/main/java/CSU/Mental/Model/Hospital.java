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
@Table(name="tab_hospital")
public class Hospital implements Serializable{

	@Id
	@Column(name="hospital_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int HospitalId;  // 主键
	
	@Column(name="hospital_name")
	private String HospitalName;  // 名称
	
	@Column(name="hospital_img")
	private String HospitalImage;  // 图片
	
	@Column(name="hospital_department")
	private String HospitalDepartment;  // 部门
	
	@Column(name="hospital_info")
	private String HospitalInfo;  // 简介

	public int getHospitalId() {
		return HospitalId;
	}

	public void setHospitalId(int hospitalId) {
		HospitalId = hospitalId;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public String getHospitalImage() {
		return HospitalImage;
	}

	public void setHospitalImage(String hospitalImage) {
		HospitalImage = hospitalImage;
	}

	public String getHospitalDepartment() {
		return HospitalDepartment;
	}

	public void setHospitalDepartment(String hospitalDepartment) {
		HospitalDepartment = hospitalDepartment;
	}

	public String getHospitalInfo() {
		return HospitalInfo;
	}

	public void setHospitalInfo(String hospitalInfo) {
		HospitalInfo = hospitalInfo;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("HospitalImage", this.HospitalImage);
		jo.put("HospitalId", this.HospitalId);
		jo.put("HospitalName", this.HospitalName);
		jo.put("HospitalDepartment", this.HospitalDepartment);
		jo.put("HospitalInfo", this.HospitalInfo);
		return jo;
	}

	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
