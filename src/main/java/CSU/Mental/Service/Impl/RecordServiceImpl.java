package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.RecordDao;
import CSU.Mental.Model.Record;
import CSU.Mental.Service.RecordService;

@Transactional
public class RecordServiceImpl implements RecordService{

	private RecordDao RecordDao;
	
	public RecordDao getRecordDao() {
		return RecordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		RecordDao = recordDao;
	}

	public boolean AddRecord(Record record) {
		// TODO Auto-generated method stub
		return RecordDao.AddRecord(record);
	}

	public boolean DeleteRecord(Record record) {
		// TODO Auto-generated method stub
		return RecordDao.DeleteRecord(record);
	}

	public boolean UpdateRecord(Record record) {
		// TODO Auto-generated method stub
		return RecordDao.UpdateRecord(record);
	}

	public Record QueryRecord(int id) {
		// TODO Auto-generated method stub
		return RecordDao.QueryRecord(id);
	}

	public List<Record> QueryRecordByUserPageSize(int UserId, int rows, int PageSize) {
		// TODO Auto-generated method stub
		return RecordDao.QueryRecordByUserPageSize(UserId, rows, PageSize);
	}

	public List<Record> QueryRecordByPatientPageSize(int PatientId, int rows, int PageSize) {
		// TODO Auto-generated method stub
		return RecordDao.QueryRecordByPatientPageSize(PatientId, rows, PageSize);
	}

	public List<Record> QueryRecordByScalePageSize(int ScaleId, int rows, int PageSize) {
		// TODO Auto-generated method stub
		return RecordDao.QueryRecordByScalePageSize(ScaleId, rows, PageSize);
	}

	public List<Record> QueryRecordPageSize(int rows, int PageSize) {
		// TODO Auto-generated method stub
		return RecordDao.QueryRecordPageSize(rows, PageSize);
	}

	public int CountRecord() {
		// TODO Auto-generated method stub
		return RecordDao.CountRecord();
	}

	public int CountRecorByUserd(int UserId) {
		// TODO Auto-generated method stub
		return RecordDao.CountRecorByUserd(UserId);
	}

	public int CountRecordByPatient(int PatientId) {
		// TODO Auto-generated method stub
		return RecordDao.CountRecordByPatient(PatientId);
	}

	public int CountRecordByScale(int ScaleId) {
		// TODO Auto-generated method stub
		return RecordDao.CountRecordByScale(ScaleId);
	}

}
