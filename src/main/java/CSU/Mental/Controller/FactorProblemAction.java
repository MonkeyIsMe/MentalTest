package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.FactorProblem;
import CSU.Mental.Service.FactorProblemService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FactorProblemAction extends ActionSupport{
	
	private CommonUtils cutil = new CommonUtils();
	private FactorProblemService FactorProblemService;
	
	public FactorProblemService getFactorProblemService() {
		return FactorProblemService;
	}
	public void setFactorProblemService(FactorProblemService factorProblemService) {
		FactorProblemService = factorProblemService;
	}
	
	//添加或更新题目因子关联
	public void AddOrUpdateFactorProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String factor_id = request.getParameter("factor_id"); 
		String problem_info = request.getParameter("problem_info");
		
		if(!cutil.IsNumber(factor_id)) {
			JSONObject jos = new JSONObject();
			jos.put("msg", "NoFactorId");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(factor_id);
		
		List<FactorProblem> add_fplist = new ArrayList<FactorProblem>();
		List<FactorProblem> fplist = FactorProblemService.QueryFactorProblemByFactor(fid);
		
		FactorProblemService.DeleteMutiplyFactorProblem(fplist);
		
		JSONArray ja = JSONArray.fromObject(problem_info);
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String problem_id = jo.getString("problem_id");
			String problem_number = jo.getString("problem_number");
			
			if(!cutil.IsNumber(problem_id)) continue;
			
			int pid = Integer.valueOf(problem_id);
			
			FactorProblem fp = new FactorProblem();
			fp.setFactorId(fid);
			fp.setProblemId(pid);
			fp.setProblemNumber(problem_number);
			
			add_fplist.add(fp);
		}
		
		FactorProblemService.AddMutiplyFactorProblem(add_fplist);
		
		JSONObject jo = new JSONObject();
		jo.put("result", "Success");
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//根据因子查题目
	public void QueryProblemByFactor() throws Exception{
		
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
			jos.put("msg", "NoFactorId");
			jos.put("code", 0);
			jos.put("PageSize", "0");
			jos.put("Array", "null");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(factor_id);
		
		List<FactorProblem> fplist = FactorProblemService.QueryFactorProblemByFactor(fid);
		
		JSONObject jo = new JSONObject();
		if (fplist.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("Array", "null");
			jo.put("msg", "NoData");
			jo.put("code", 0);
		} 
		else {
			JSONArray ja = JSONArray.fromObject(fplist);
			jo.put("Count", fplist.size());
			jo.put("rows", 1);
			jo.put("msg", "Accept");
			jo.put("code", 0);
			jo.put("PageSize", fplist.size());
			jo.put("Array", ja.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
}
