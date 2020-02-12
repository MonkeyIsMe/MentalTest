package CSU.Mental.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Factor;
import CSU.Mental.Model.Reference;
import CSU.Mental.Service.FactorService;
import CSU.Mental.Service.ReferenceService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FactorAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private FactorService FactorService;
	private ReferenceService ReferenceService;
	private Factor factor = new Factor();
	private Reference reference = new Reference();
	
	public FactorService getFactorService() {
		return FactorService;
	}
	public void setFactorService(FactorService factorService) {
		FactorService = factorService;
	}
	public ReferenceService getReferenceService() {
		return ReferenceService;
	}
	public void setReferenceService(ReferenceService referenceService) {
		ReferenceService = referenceService;
	}
	
	//添加量表因子
	public void AddScaleFactor() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String FactorInfo = request.getParameter("factor_info");
		String scale_id = request.getParameter("scale_id");
		
		int sid = Integer.valueOf(scale_id);
		
		JSONArray ja = JSONArray.fromObject(FactorInfo);
		
		for(int i = 0; i < ja.size(); i ++) {
			
			JSONObject jo = ja.getJSONObject(i);
			String factor_name = jo.getString("factor_name");
			String factor_info = jo.getString("factor_info");
			String factor_balance = jo.getString("factor_balance");
			String factor_den = jo.getString("factor_den");
			String factor_formula = jo.getString("factor_formula");
			String factor_order = jo.getString("factor_order");
			String refer_info = jo.getString("refer_info");
			
			//处理因子
			Factor fac = new Factor();
			fac.setFactorName(factor_name);
			fac.setFactorInfo(factor_info);
			fac.setFactorBalance(Double.valueOf(factor_balance));
			fac.setFactorDen(Double.valueOf(factor_den));
			fac.setFactorFormula(factor_formula);
			fac.setFactorOrder(factor_order);
			fac.setScaleId(sid);
			
			int fid = FactorService.AddFactor(fac);
			
			//处理参考意见范围
			JSONArray rja = JSONArray.fromObject(refer_info);
			for(int j = 0; j < rja.size(); j ++) {
				
				JSONObject rjo = rja.getJSONObject(j);
				//System.out.println(rjo.toString());
				String reference_bscore = rjo.getString("refer_bscore");
				String reference_escore = rjo.getString("refer_escore");
				String reference_sex = rjo.getString("refer_sex");
				String reference_bage = rjo.getString("refer_bage");
				String reference_eage = rjo.getString("refer_eage");
				String reference_suggestion = rjo.getString("refer_suggestion");
				
				Reference refer = new Reference();
				refer.setReferenceBeginScore(Integer.valueOf(reference_bscore));
				refer.setReferenceEndScore(Integer.valueOf(reference_escore));
				refer.setReferenceSex(reference_sex);
				refer.setReferenceBeginAge(Integer.valueOf(reference_bage));
				refer.setReferenceEndAge(Integer.valueOf(reference_eage));
				refer.setReferenceSuggestion(reference_suggestion);
				refer.setFactorId(fid);
				
				ReferenceService.AddReference(refer);
				
			}
			
		}
		
		out.println("Success");
		out.flush();
		out.close();
		return;
		
	}

}
