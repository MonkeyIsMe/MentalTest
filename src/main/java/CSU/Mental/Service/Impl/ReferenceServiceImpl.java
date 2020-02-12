package CSU.Mental.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.Mental.Dao.ReferenceDao;
import CSU.Mental.Model.Reference;
import CSU.Mental.Service.ReferenceService;

@Transactional
public class ReferenceServiceImpl implements ReferenceService{

	private ReferenceDao ReferenceDao;
	
	public ReferenceDao getReferenceDao() {
		return ReferenceDao;
	}

	public void setReferenceDao(ReferenceDao referenceDao) {
		ReferenceDao = referenceDao;
	}

	public boolean AddReference(Reference refer) {
		// TODO Auto-generated method stub
		return ReferenceDao.AddReference(refer);
	}

	public boolean UpdateReference(Reference refer) {
		// TODO Auto-generated method stub
		return ReferenceDao.UpdateReference(refer);
	}

	public boolean DeleteReference(Reference refer) {
		// TODO Auto-generated method stub
		return ReferenceDao.DeleteReference(refer);
	}

	public Reference QueryReference(int id) {
		// TODO Auto-generated method stub
		return ReferenceDao.QueryReference(id);
	}

	public List<Reference> QueryReferenceByFactor(int FactorId) {
		// TODO Auto-generated method stub
		return ReferenceDao.QueryReferenceByFactor(FactorId);
	}

	public Object DeleteMutiplyReference(List<Reference> list) {
		// TODO Auto-generated method stub
		return ReferenceDao.DeleteMutiplyReference(list);
	}

}
