package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Reference;
import CSU.Mental.Service.ReferenceService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReferenceAction extends ActionSupport{
	
	private CommonUtils cutil = new CommonUtils();
	private ReferenceService ReferenceService;
	private Reference reference = new Reference();
	
	public ReferenceService getReferenceService() {
		return ReferenceService;
	}
	public void setReferenceService(ReferenceService referenceService) {
		ReferenceService = referenceService;
	}
	
	//删除一个参考选项
	public void DeteleReference() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String reference_id = request.getParameter("reference_id"); 
		
		if(!cutil.IsNumber(reference_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int rid = Integer.valueOf(reference_id);
	
		reference = ReferenceService.QueryReference(rid);
		if(reference == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		flag = ReferenceService.DeleteReference(reference);
		
		if(flag) {
			out.println("Success");
			out.flush();
			out.close();
			return;
		}
		else {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
	}

	//添加一个参考选项
	public void AddReference() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String reference_bscore = request.getParameter("refer_bscore");
		String reference_escore = request.getParameter("refer_escore");
		String reference_sex = request.getParameter("refer_sex");
		String reference_bage = request.getParameter("refer_bage");
		String reference_eage = request.getParameter("refer_eage");
		String reference_suggestion = request.getParameter("refer_suggestion");
		String factor_id = request.getParameter("factor_id");
		
		if(!cutil.IsNumber(factor_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(factor_id);
		
		reference.setReferenceBeginScore(Integer.valueOf(reference_bscore));
		reference.setReferenceEndScore(Integer.valueOf(reference_escore));
		reference.setReferenceSex(reference_sex);
		reference.setReferenceBeginAge(Integer.valueOf(reference_bage));
		reference.setReferenceEndAge(Integer.valueOf(reference_eage));
		reference.setReferenceSuggestion(reference_suggestion);
		reference.setFactorId(fid);
		
		flag = ReferenceService.AddReference(reference);
		
		if(flag) {
			out.println("Success");
			out.flush();
			out.close();
			return;
		}
		else {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
	}
	
	//添加一组参考选项
	public void AddMutiplyReference() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String ReferenceInfo = request.getParameter("reference_info"); 
		String factor_id = request.getParameter("factor_id");
		
		if(!cutil.IsNumber(factor_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(factor_id);
		
		JSONArray ja = JSONArray.fromObject(ReferenceInfo);
		List<Reference> rlist = new ArrayList<Reference>();
		for(int i = 0; i < ja.size(); i ++) {
			
			JSONObject jo = new JSONObject(); 
			
			String reference_bscore = jo.getString("refer_bscore");
			String reference_escore = jo.getString("refer_escore");
			String reference_sex = jo.getString("refer_sex");
			String reference_bage = jo.getString("refer_bage");
			String reference_eage = jo.getString("refer_eage");
			String reference_suggestion = jo.getString("refer_suggestion");
			
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
		
		out.println("Success");
		out.flush();
		out.close();
		return;
		
	}
	
}
