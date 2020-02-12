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
@Table(name="tab_skind")
public class Skind implements Serializable{

	@Id
	@Column(name="sk_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SkindId;  // 主键
	
	@Column(name="fk_id")
	private int FkindId;  // 大类主键
	
	@Column(name="skind_name")
	private String SkindName;  // 小类名字

	public int getSkindId() {
		return SkindId;
	}

	public void setSkindId(int skindId) {
		SkindId = skindId;
	}

	public int getFkindId() {
		return FkindId;
	}

	public void setFkindId(int fkindId) {
		FkindId = fkindId;
	}

	public String getSkindName() {
		return SkindName;
	}

	public void setSkindName(String skindName) {
		SkindName = skindName;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("SkindId", this.SkindId);
		jo.put("FkindId", this.FkindId);
		jo.put("SkindName", this.SkindName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
