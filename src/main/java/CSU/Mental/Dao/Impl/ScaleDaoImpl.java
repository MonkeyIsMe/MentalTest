package CSU.Mental.Dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ScaleDao;
import CSU.Mental.Model.Patient;
import CSU.Mental.Model.Scale;

@Transactional
public class ScaleDaoImpl extends HibernateDaoSupport implements ScaleDao{

	public int AddScale(Scale scale) {
		// TODO Auto-generated method stub
		int sid = 0;
		
		try {
			getHibernateTemplate().save(scale);
			sid = scale.getScaleId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sid;
	}

	public boolean UpdtaeScale(Scale scale) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().update(scale);
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public boolean DeleteScale(Scale scale) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			getHibernateTemplate().delete(scale);
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	public Scale QueryScale(int id) {
		// TODO Auto-generated method stub
		Scale result = null;
		try {
			result = getHibernateTemplate().get(Scale.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int CountScale() {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Scale as Scale"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public List<Scale> QueryScalePageSize(final int PageSize, final int rows) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Scale>>() {

				public List<Scale> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Scale";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					List<Scale> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Scale> QueryScaleBySkindPageSize(final int PageSize, final int rows, final int SkindId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Scale>>() {

				public List<Scale> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Scale where sk_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, SkindId);
					List<Scale> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Scale> QueryScaleByFkindPageSize(final int PageSize, final int rows, final int FkindId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Scale>>() {

				public List<Scale> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Scale where fk_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, FkindId);
					List<Scale> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountScaleByFkind(int FkindId) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Scale as scale where fk_id = '"+ FkindId +"'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int CountScaleBySkind(int SkindId) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Scale as scale where sk_id = '"+ SkindId +"'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int CountScaleBySkindFkind(int SkindId, int FkindId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Scale as scale where sk_id = '"+SkindId+"'" +"AND fk_id = '"+FkindId+"'";  
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Scale> QueryScaleBySkindFkindPageSize(final int PageSize, final int rows, final int SkindId, final int FkindId) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Scale>>() {

				public List<Scale> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Scale where fk_id = ? AND sk_id = ?";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setParameter(0, FkindId);
					query.setParameter(1, SkindId);
					List<Scale> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int CountVagueScale(String name) {
		// TODO Auto-generated method stub
		int count = - 1;
		try {
			String hql = "select count(*) from Scale as scale where patient_name like '%"+ name +"%'"; 
			return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public List<Scale> VagueScalePageSize(final int PageSize, final int rows, final String name) {
		// TODO Auto-generated method stub
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Scale>>() {

				public List<Scale> doInHibernate(Session session) throws HibernateException {
					// TODO Auto-generated method stub
					String hql = "from Patient where user_name like :name";
					Query query = session.createQuery(hql).setFirstResult(
			                (rows - 1) * PageSize).setMaxResults(PageSize);
					query.setString("name", "%"+ name +"%");
					List<Scale> list = query.list();
					return list;
				}
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
