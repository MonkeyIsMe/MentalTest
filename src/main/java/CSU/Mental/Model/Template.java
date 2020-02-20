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
@Table(name="tab_template")
public class Template implements Serializable{

	@Id
	@Column(name="template_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int TemplateId;  // 主键
	
	@Column(name="template_name")
	private String TemplateName;  // 模板名称
	
	@Column(name="template_number")
	private int TemplateNumber;  // 关联题目数量

	public int getTemplateId() {
		return TemplateId;
	}

	public void setTemplateId(int templateId) {
		TemplateId = templateId;
	}

	public String getTemplateName() {
		return TemplateName;
	}

	public void setTemplateName(String templateName) {
		TemplateName = templateName;
	}
	
	public int getTemplateNumber() {
		return TemplateNumber;
	}

	public void setTemplateNumber(int templateNumber) {
		TemplateNumber = templateNumber;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("TemplateId", this.TemplateId);
		jo.put("TemplateName", this.TemplateName);
		jo.put("TemplateNumber", this.TemplateNumber);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
