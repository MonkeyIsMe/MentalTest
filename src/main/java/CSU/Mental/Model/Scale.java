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
@Table(name="tab_scale")
public class Scale implements Serializable{

	@Id
	@Column(name="scale_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ScaleId;  // 主键
	
	@Column(name="scale_name")
	private String ScaleName;  // 量表名称
	
	@Column(name="scale_info")
	private String ScaleInfo;  // 量表信息
	
	@Column(name="scale_guide")
	private String ScaleGuide;  // 量表指导语
	
	@Column(name="sk_id")
	private int SkinId;  // 小类编号
	
	@Column(name="fk_id")
	private int FkinId;  // 大类编号
	
	@Column(name="scale_update")
	private String ScaleUpdateTime;  // 更新时间
	
	@Column(name="scale_number")
	private int ScaleNumber;  // 使用次数
	
	@Column(name="scale_type2")
	private String ScaleType2;  //备用
	
	@Column(name="scale_type3")
	private String ScaleType3;  //备用
	
	@Column(name="scale_type4")
	private String ScaleType4;  //备用

	public int getScaleId() {
		return ScaleId;
	}

	public void setScaleId(int scaleId) {
		ScaleId = scaleId;
	}

	public String getScaleName() {
		return ScaleName;
	}

	public void setScaleName(String scaleName) {
		ScaleName = scaleName;
	}

	public String getScaleInfo() {
		return ScaleInfo;
	}

	public void setScaleInfo(String scaleInfo) {
		ScaleInfo = scaleInfo;
	}

	public String getScaleGuide() {
		return ScaleGuide;
	}

	public void setScaleGuide(String scaleGuide) {
		ScaleGuide = scaleGuide;
	}

	public int getSkinId() {
		return SkinId;
	}

	public void setSkinId(int skinId) {
		SkinId = skinId;
	}

	public String getScaleUpdateTime() {
		return ScaleUpdateTime;
	}

	public void setScaleUpdateTime(String scaleUpdateTime) {
		ScaleUpdateTime = scaleUpdateTime;
	}
	
	public int getScaleNumber() {
		return ScaleNumber;
	}

	public void setScaleNumber(int scaleNumber) {
		ScaleNumber = scaleNumber;
	}

	public String getScaleType2() {
		return ScaleType2;
	}

	public void setScaleType2(String scaleType2) {
		ScaleType2 = scaleType2;
	}

	public String getScaleType3() {
		return ScaleType3;
	}

	public void setScaleType3(String scaleType3) {
		ScaleType3 = scaleType3;
	}

	public String getScaleType4() {
		return ScaleType4;
	}

	public void setScaleType4(String scaleType4) {
		ScaleType4 = scaleType4;
	}
	
	public int getFkinId() {
		return FkinId;
	}

	public void setFkinId(int fkinId) {
		FkinId = fkinId;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("ScaleId", this.ScaleId);
		jo.put("ScaleName", this.ScaleName);
		jo.put("ScaleInfo", this.ScaleInfo);
		jo.put("ScaleGuide", this.ScaleGuide);
		jo.put("SkinId", this.SkinId);
		jo.put("FkinId", this.FkinId);
		jo.put("ScaleUpdateTime", this.ScaleUpdateTime);
		jo.put("ScaleNumber", this.ScaleNumber);
		jo.put("ScaleType2", this.ScaleType2);
		jo.put("ScaleType3", this.ScaleType3);
		jo.put("ScaleType4", this.ScaleType4);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
