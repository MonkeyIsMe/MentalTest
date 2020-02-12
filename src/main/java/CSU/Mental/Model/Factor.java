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
@Table(name="tab_factor")
public class Factor implements Serializable{

	@Id
	@Column(name="factor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FactorId;  // 主键
	
	@Column(name="scale_id")
	private int ScaleId;  // 量表编号
	
	@Column(name="factor_name")
	private String FactorName;  // 名称
	
	@Column(name="factor_info")
	private String FactorInfo;  // 详细信息
	
	@Column(name="factor_balance")
	private double FactorBalance;  // 比例
	
	@Column(name="factor_den")
	private double FactorDen;  
	
	@Column(name="factor_onum")
	private String FactorOnum;  
	
	@Column(name="factor_type")
	private String FactorType; 
	
	@Column(name="factor_formula")
	private String FactorFormula;  
	
	@Column(name="factor_order")
	private String FactorOrder; 

	public int getFactorId() {
		return FactorId;
	}

	public void setFactorId(int factorId) {
		FactorId = factorId;
	}

	public int getScaleId() {
		return ScaleId;
	}

	public void setScaleId(int scaleId) {
		ScaleId = scaleId;
	}

	public String getFactorName() {
		return FactorName;
	}

	public void setFactorName(String factorName) {
		FactorName = factorName;
	}

	public String getFactorInfo() {
		return FactorInfo;
	}

	public void setFactorInfo(String factorInfo) {
		FactorInfo = factorInfo;
	}

	public double getFactorBalance() {
		return FactorBalance;
	}

	public void setFactorBalance(double factorBalance) {
		FactorBalance = factorBalance;
	}

	public double getFactorDen() {
		return FactorDen;
	}

	public void setFactorDen(double factorDen) {
		FactorDen = factorDen;
	}

	public String getFactorOnum() {
		return FactorOnum;
	}

	public void setFactorOnum(String factorOnum) {
		FactorOnum = factorOnum;
	}

	public String getFactorType() {
		return FactorType;
	}

	public void setFactorType(String factorType) {
		FactorType = factorType;
	}

	public String getFactorFormula() {
		return FactorFormula;
	}

	public void setFactorFormula(String factorFormula) {
		FactorFormula = factorFormula;
	}

	public String getFactorOrder() {
		return FactorOrder;
	}

	public void setFactorOrder(String factorOrder) {
		FactorOrder = factorOrder;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("FactorId", this.FactorId);
		jo.put("ScaleId", this.ScaleId);
		jo.put("FactorName", this.FactorName);
		jo.put("FactorInfo", this.FactorInfo);
		jo.put("FactorBalance", this.FactorBalance);
		jo.put("FactorDen", this.FactorDen);
		jo.put("FactorOnum", this.FactorOnum);
		jo.put("FactorType", this.FactorType);
		jo.put("FactorFormula", this.FactorFormula);
		jo.put("FactorOrder", this.FactorOrder);
		return jo;
	}

	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
