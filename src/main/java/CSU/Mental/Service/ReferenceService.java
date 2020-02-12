package CSU.Mental.Service;

import java.util.List;

import CSU.Mental.Model.Reference;

public interface ReferenceService {
	
	public boolean AddReference(Reference refer);
	
	public boolean UpdateReference(Reference refer);
	
	public boolean DeleteReference(Reference refer);
	
	public Reference QueryReference(int id);
	
	public List<Reference> QueryReferenceByFactor(int FactorId);
	
	public Object DeleteMutiplyReference(List<Reference> list);
	
}
