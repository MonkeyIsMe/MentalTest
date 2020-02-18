package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Fkind;
import CSU.Mental.Service.FkindService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FkindAction extends ActionSupport{
	
	private CommonUtils cutil = new CommonUtils();
	private FkindService FkindService;
	private Fkind fkind = new Fkind();
	
	public FkindService getFkindService() {
		return FkindService;
	}
	public void setFkindService(FkindService fkindService) {
		FkindService = fkindService;
	}
	
	//增加一个大类
	public void AddFkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String fkind_name = request.getParameter("fkind_name");
		
		fkind.setFkindName(fkind_name);
		
		flag = FkindService.AddFkind(fkind);
		
		JSONObject jo = new JSONObject();
		if (flag) {
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
	
	//更新一个大类
	public void UpdateFkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String fkind_id = request.getParameter("fkind_id");
		String fkind_name = request.getParameter("fkind_name");
		
		if(!cutil.IsNumber(fkind_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(fkind_id);
		
		fkind = FkindService.QueryFkind(fid);
		
		if(fkind == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		fkind.setFkindName(fkind_name);
		
		flag = FkindService.UpdateFkind(fkind);
		
		JSONObject jo = new JSONObject();
		if (flag) {
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
	
	//删除一个大类
	public void DeleteFkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String fkind_id = request.getParameter("fkind_id");
		
		if(!cutil.IsNumber(fkind_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(fkind_id);
		
		fkind = FkindService.QueryFkind(fid);
		
		if(fkind == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		
		flag = FkindService.DeleteFkind(fkind);
		
		JSONObject jo = new JSONObject();
		if (flag) {
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
	
	//查询所有大类
	public void QueryAllFkind() throws Exception {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		List<Fkind> FkindList = FkindService.QueryAllFkind();

		JSONObject jo = new JSONObject();

		if (FkindList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("msg", "");
			jo.put("code", 0);
			jo.put("Array", "null");
		} 
		else {
			JSONArray ja = JSONArray.fromObject(FkindList);
			jo.put("Count", FkindList.size());
			jo.put("rows", 1);
			jo.put("msg", "");
			jo.put("code", 0);
			jo.put("PageSize", FkindList.size());
			jo.put("Array", ja.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//查询所有大类总数
	public void CountFkind() throws Exception {

		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		// 返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int count = FkindService.CountFkind();
		JSONObject jo = new JSONObject();
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
}
