package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Scale;

public interface ScaleDao {
	
	public int AddScale(Scale scale);
	
	public boolean UpdtaeScale(Scale scale);
	
	public boolean DeleteScale(Scale scale);
	
	public Scale QueryScale(int id);
	
	public int CountScale();
	
	public int CountScaleByFkind(int FkindId);
	
	public int CountScaleBySkind(int SkindId);
	
	public int CountVagueScale(String name);
	
	public int CountScaleBySkindFkind(int SkindId,int FkindId);
	
	public List<Scale> QueryScalePageSize(int PageSize,int rows);
	
	public List<Scale> VagueScalePageSize(int PageSize,int rows,String name);
	
	public List<Scale> QueryScaleBySkindPageSize(int PageSize,int rows,int SkindId);
	
	public List<Scale> QueryScaleBySkindFkindPageSize(int PageSize,int rows,int SkindId,int FkindId);
	
	public List<Scale> QueryScaleByFkindPageSize(int PageSize,int rows,int FkindId);
	
}
