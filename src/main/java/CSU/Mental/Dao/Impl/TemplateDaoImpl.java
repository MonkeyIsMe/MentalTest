package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.TemplateDao;
import CSU.Mental.Model.Scale;
import CSU.Mental.Model.Template;

@Transactional
public class TemplateDaoImpl extends HibernateDaoSupport implements TemplateDao{

	public int AddTemplate(Template tmp) {
		// TODO Auto-generated method stub
		int count = -1;
		try {
			getHibernateTemplate().save(tmp);
			count = tmp.getTemplateId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public boolean DeleteTemplate(Template tmp) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(tmp);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return false;
	}

	public Template QueryTemplate(int id) {
		// TODO Auto-generated method stub
		Template result = null;
		try {
			result = getHibernateTemplate().get(Template.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean UpdateTemplate(Template tmp) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(tmp);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return false;
	}

	public int CountTemplate() {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Template as template"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public List<Template> QueryAllTemplate() {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Template>>() {

				public List<Template> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Template";
					Query query = session.createQuery(hql);
					List<Template> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Template> VagueTemplatePageSize(final String name, final int PageSize, final int rows) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Template>>() {

				public List<Template> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Template where template_name like :name";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setString("name", "%"+ name +"%");
					List<Template> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Template> QueryTemplatePageSize(final int PageSize, final int rows) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Template>>() {

				public List<Template> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Template order by TemplateNumber desc";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					List<Template> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountVagueTemplate(String name) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Template as template where template_name like '%"+ name +"%'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
