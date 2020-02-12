package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ScaleDao;
import CSU.Mental.Model.Scale;
import CSU.Mental.Service.ScaleService;

@Transactional
public class ScaleServiceImpl implements ScaleService{

	private ScaleDao ScaleDao;
	
	public ScaleDao getScaleDao() {
		return ScaleDao;
	}

	public void setScaleDao(ScaleDao scaleDao) {
		ScaleDao = scaleDao;
	}

	public int AddScale(Scale scale) {
		// TODO Auto-generated method stub
		return ScaleDao.AddScale(scale);
	}

	public boolean UpdtaeScale(Scale scale) {
		// TODO Auto-generated method stub
		return ScaleDao.UpdtaeScale(scale);
	}

	public boolean DeleteScale(Scale scale) {
		// TODO Auto-generated method stub
		return ScaleDao.DeleteScale(scale);
	}

	public Scale QueryScale(int id) {
		// TODO Auto-generated method stub
		return ScaleDao.QueryScale(id);
	}

	public int CountScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Scale> QueryScalePageSize(int PageSize, int rows) {
		// TODO Auto-generated method stub
		return ScaleDao.QueryScalePageSize(PageSize, rows);
	}

	public List<Scale> QueryScaleBySkindPageSize(int PageSize, int rows, int SkindId) {
		// TODO Auto-generated method stub
		return ScaleDao.QueryScaleBySkindPageSize(PageSize, rows, SkindId);
	}

	public List<Scale> QueryScaleByFkindPageSize(int PageSize, int rows, int FkindId) {
		// TODO Auto-generated method stub
		return ScaleDao.QueryScaleByFkindPageSize(PageSize, rows, FkindId);
	}

	public int CountScaleByFkind(int FkindId) {
		// TODO Auto-generated method stub
		return ScaleDao.CountScaleByFkind(FkindId);
	}

	public int CountScaleBySkind(int SkindId) {
		// TODO Auto-generated method stub
		return ScaleDao.CountScaleBySkind(SkindId);
	}

	public List<Scale> QueryScaleBySkindFkindPageSize(int PageSize, int rows, int SkindId, int FkindId) {
		// TODO Auto-generated method stub
		return ScaleDao.QueryScaleBySkindFkindPageSize(PageSize, rows, SkindId, FkindId);
	}

	public int CountScaleBySkindFkind(int SkindId, int FkindId) {
		// TODO Auto-generated method stub
		return ScaleDao.CountScale();
	}

	public int CountVagueScale(String name) {
		// TODO Auto-generated method stub
		return ScaleDao.CountVagueScale(name);
	}

	public List<Scale> VagueScalePageSize(int PageSize, int rows, String name) {
		// TODO Auto-generated method stub
		return ScaleDao.VagueScalePageSize(PageSize, rows, name);
	}

}
