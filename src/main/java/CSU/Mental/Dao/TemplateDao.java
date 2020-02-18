package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Template;

public interface TemplateDao {
	
	public int AddTemplate(Template tmp);
	
	public boolean DeleteTemplate(Template tmp);
	
	public Template QueryTemplate(int id);
	
	public boolean UpdateTemplate(Template tmp);
	
	public int CountTemplate();
	
	public int CountVagueTemplate(String name);
	
	public List<Template> QueryAllTemplate();
	
	public List<Template> VagueTemplatePageSize(String name,int PageSize,int rows);
	
	public List<Template> QueryTemplatePageSize(int PageSize,int rows);
	
}
