package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Skind;
import CSU.Mental.Service.SkindService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONObject;

public class SkindAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private SkindService SkindService;
	private Skind skind = new Skind();
	
	public SkindService getSkindService() {
		return SkindService;
	}
	public void setSkindService(SkindService skindService) {
		SkindService = skindService;
	}
	
	//增加一个小类
	public void AddSkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String fk_id = request.getParameter("fk_id");
		String skind_name = request.getParameter("skind_name");
		
		if(!cutil.IsNumber(fk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fkid = Integer.valueOf(fk_id);
		
		skind.setFkindId(fkid);
		skind.setSkindName(skind_name);
		
		flag = SkindService.AddSkind(skind);
		
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
	
	//更新一个小类
	public void UpdateSkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String sk_id = request.getParameter("sk_id");
		String skind_name = request.getParameter("skind_name");
		
		if(!cutil.IsNumber(sk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int skid = Integer.valueOf(sk_id);
		
		skind = SkindService.QuerySkind(skid);
		
		if(skind == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		skind.setSkindName(skind_name);
		
		flag = SkindService.UpdateSkind(skind);
		
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

	//根据大类查询所有小类
	public void QueryAllSkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String fk_id = request.getParameter("fk_id");
		
		if(!cutil.IsNumber(fk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("Count", "0");
			jos.put("rows", "0");
			jos.put("PageSize", "0");
			jos.put("Array", "null");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fkid = Integer.valueOf(fk_id);
		
		skind = SkindService.QuerySkind(fkid);
		
		List<Skind> SkindList = SkindService.QuerySkindByFkind(fkid);
		
		JSONObject jo = new JSONObject();
		if(SkindList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", SkindList.size());
			jo.put("rows", 1);
			jo.put("PageSize", SkindList.size());
			jo.put("Array", SkindList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
	
	//删除一个小类
	public void DeleteSkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String sk_id = request.getParameter("sk_id");
		
		if(!cutil.IsNumber(sk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int skid = Integer.valueOf(sk_id);
		
		skind = SkindService.QuerySkind(skid);
		
		if(skind == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		flag = SkindService.DeleteSkind(skind);
		
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

	
}
