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
	//更新参考选项
	public void UpdateReference() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String reference_id = request.getParameter("reference_id"); 
		String reference_bscore = request.getParameter("refer_bscore");
		String reference_escore = request.getParameter("refer_escore");
		String reference_sex = request.getParameter("refer_sex");
		String reference_bage = request.getParameter("refer_bage");
		String reference_eage = request.getParameter("refer_eage");
		String reference_suggestion = request.getParameter("refer_suggestion");
		
		if(!cutil.IsNumber(reference_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int rid = Integer.valueOf(reference_id);
	
		reference = ReferenceService.QueryReference(rid);
		
		if(reference == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		reference.setReferenceBeginScore(Integer.valueOf(reference_bscore));
		reference.setReferenceEndScore(Integer.valueOf(reference_escore));
		reference.setReferenceSex(reference_sex);
		reference.setReferenceBeginAge(Integer.valueOf(reference_bage));
		reference.setReferenceEndAge(Integer.valueOf(reference_eage));
		reference.setReferenceSuggestion(reference_suggestion);
		
		flag = ReferenceService.UpdateReference(reference);
		
		JSONObject jo = new JSONObject();
		if(flag) {
			jo.put("result", "Success");
			out.println(jo.toString());
			out.flush();
			out.close();
			return;
		}
		else {
			jo.put("result", "Fail");
			out.println(jo.toString());
			out.flush();
			out.close();
			return;
		}
	}
	
	//删除一个参考选项
	public void DeleteReference() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String reference_id = request.getParameter("reference_id"); 
		
		if(!cutil.IsNumber(reference_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int rid = Integer.valueOf(reference_id);
	
		reference = ReferenceService.QueryReference(rid);
		if(reference == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		flag = ReferenceService.DeleteReference(reference);
		
		JSONObject jo = new JSONObject();
		if(flag) {
			jo.put("result", "Success");
			out.println(jo.toString());
			out.flush();
			out.close();
			return;
		}
		else {
			jo.put("result", "Fail");
			out.println(jo.toString());
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
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
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
		
		JSONObject jo = new JSONObject();
		if(flag) {
			jo.put("result", "Success");
			out.println(jo.toString());
			out.flush();
			out.close();
			return;
		}
		else {
			jo.put("result", "Fail");
			out.println(jo.toString());
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
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(factor_id);
		
		JSONArray ja = JSONArray.fromObject(ReferenceInfo);
		List<Reference> rlist = new ArrayList<Reference>();
		for(int i = 0; i < ja.size(); i ++) {
			
			JSONObject jo = new JSONObject(); 
			
			String refer_id = jo.getString("refer_id");
			String reference_bscore = jo.getString("refer_bscore");
			String reference_escore = jo.getString("refer_bscore");
			String reference_sex = jo.getString("refer_sex");
			String reference_bage = jo.getString("refer_bage");
			String reference_eage = jo.getString("refer_eage");
			String reference_suggestion = jo.getString("refer_suggestion");
			
			int rid = Integer.valueOf(refer_id);
			if(rid == 0) {
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
			else {
				Reference refer = ReferenceService.QueryReference(rid);
				refer.setReferenceBeginScore(Integer.valueOf(reference_bscore));
				refer.setReferenceEndScore(Integer.valueOf(reference_escore));
				refer.setReferenceSex(reference_sex);
				refer.setReferenceBeginAge(Integer.valueOf(reference_bage));
				refer.setReferenceEndAge(Integer.valueOf(reference_eage));
				refer.setReferenceSuggestion(reference_suggestion);
				refer.setFactorId(fid);
				
				ReferenceService.UpdateReference(refer);
			}

		}
		
		JSONObject jo = new JSONObject();
		jo.put("result", "Success");
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
	

	//根据因子查询参考信息
	public void QueryReferenceByFactor() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String factor_id = request.getParameter("factor_id");
		
		if(!cutil.IsNumber(factor_id)) {
			JSONObject jos = new JSONObject();
			jos.put("Count", "0");
			jos.put("rows", "0");
			jos.put("PageSize", "0");
			jos.put("Array", "null");
			jos.put("msg", "");
			jos.put("code", 0);
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(factor_id);
		
		List<Reference> rlist = ReferenceService.QueryReferenceByFactor(fid);
		
		JSONObject jo = new JSONObject();

		if (rlist.size() == 0) {
			jo.put("Count", "0");
			jo.put("msg", "");
			jo.put("code", 0);
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("Array", "null");
		} 
		else {
			JSONArray ja = JSONArray.fromObject(rlist);
			jo.put("Count", rlist.size());
			jo.put("msg", "");
			jo.put("code", 0);
			jo.put("rows", 1);
			jo.put("PageSize", rlist.size());
			jo.put("Array", ja.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
}
