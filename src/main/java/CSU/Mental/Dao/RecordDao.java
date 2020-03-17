package CSU.Mental.Dao;

import java.util.List;

import CSU.Mental.Model.Record;

public interface RecordDao {
	
	public int AddRecord(Record record);
	
	public boolean DeleteRecord(Record record);
	
	public boolean UpdateRecord(Record record);
	
	public Record QueryRecord(int id);
	
	public List<Record> QueryRecordByUserPageSize(int UserId,int rows,int PageSize);
	
	public List<Record> QueryRecordByPatientPageSize(int PatientId,int rows,int PageSize);
	
	public List<Record> QueryRecordByScalePageSize(int ScaleId,int rows,int PageSize);
	
	public List<Record> QueryRecordPageSize(int rows,int PageSize);
	
	public int CountRecord();
	
	public int CountRecorByUserd(int UserId);
	
	public int CountRecordByPatient(int PatientId);
	
	public int CountRecordByScale(int ScaleId);
	
}
