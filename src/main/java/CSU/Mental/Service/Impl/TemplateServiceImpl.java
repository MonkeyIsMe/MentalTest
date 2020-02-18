package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.TemplateDao;
import CSU.Mental.Model.Template;
import CSU.Mental.Service.TemplateService;

@Transactional
public class TemplateServiceImpl implements TemplateService{

	private TemplateDao TemplateDao;
	
	public TemplateDao getTemplateDao() {
		return TemplateDao;
	}

	public void setTemplateDao(TemplateDao templateDao) {
		TemplateDao = templateDao;
	}

	public int AddTemplate(Template tmp) {
		// TODO Auto-generated method stub
		return TemplateDao.AddTemplate(tmp);
	}

	public boolean DeleteTemplate(Template tmp) {
		// TODO Auto-generated method stub
		return TemplateDao.DeleteTemplate(tmp);
	}

	public Template QueryTemplate(int id) {
		// TODO Auto-generated method stub
		return TemplateDao.QueryTemplate(id);
	}

	public boolean UpdateTemplate(Template tmp) {
		// TODO Auto-generated method stub
		return TemplateDao.UpdateTemplate(tmp);
	}

	public int CountTemplate() {
		// TODO Auto-generated method stub
		return TemplateDao.CountTemplate();
	}

	public List<Template> QueryAllTemplate() {
		// TODO Auto-generated method stub
		return TemplateDao.QueryAllTemplate();
	}

	public List<Template> VagueTemplatePageSize(String name, int PageSize, int rows) {
		// TODO Auto-generated method stub
		return TemplateDao.VagueTemplatePageSize(name, PageSize, rows);
	}

	public List<Template> QueryTemplatePageSize(int PageSize, int rows) {
		// TODO Auto-generated method stub
		return TemplateDao.QueryTemplatePageSize(PageSize, rows);
	}

	public int CountVagueTemplate(String name) {
		// TODO Auto-generated method stub
		return TemplateDao.CountVagueTemplate(name);
	}

}
