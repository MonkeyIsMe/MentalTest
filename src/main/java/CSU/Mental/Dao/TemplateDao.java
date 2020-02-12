package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Template;

public interface TemplateDao {
	
	public int AddTemplate(Template tmp);
	
	public boolean DeleteTemplate(Template tmp);
	
	public Template QueryTemplate(int id);
	
	public boolean UpdateTemplate(Template tmp);
	
	public int CountTemplate();
	
	public List<Template> QueryAllTemplate();
	
}
