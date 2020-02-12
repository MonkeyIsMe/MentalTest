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
@Table(name="tab_fkind")
public class Fkind implements Serializable{

	@Id
	@Column(name="fk_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FkindId;  // 主键
	
	@Column(name="fkind_name")
	private String FkindName;  // 大类名字

	public int getFkindId() {
		return FkindId;
	}

	public void setFkindId(int fkindId) {
		FkindId = fkindId;
	}

	public String getFkindName() {
		return FkindName;
	}

	public void setFkindName(String fkindName) {
		FkindName = fkindName;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("FkindId", this.FkindId);
		jo.put("FkindName", this.FkindName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
