package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Choice;
import CSU.Mental.Service.ChoiceService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChoiceAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private ChoiceService ChoiceService;
	private Choice choice = new Choice();
	
	public ChoiceService getChoiceService() {
		return ChoiceService;
	}
	public void setChoiceService(ChoiceService choiceService) {
		ChoiceService = choiceService;
	}
	
	//删除一个选项
	public void DeleteChoice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String choice_id = request.getParameter("choice_id"); 
		
		if(!cutil.IsNumber(choice_id)) {
			///System.out.println(choice_id);
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int cid = Integer.valueOf(choice_id);
		
		choice = ChoiceService.QueryChoice(cid);
		//System.out.println(choice.toString());
		if(choice == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		flag = ChoiceService.DeleteChoice(choice);
		
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
	
	//增加一个选项
	public void AddChoice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String choice_type = request.getParameter("choice_type"); 
		String choice_info = request.getParameter("choice_info"); 
		String choice_sub = request.getParameter("choice_sub"); 
		String choice_score = request.getParameter("choice_score"); 
		String choice_flag = request.getParameter("choice_flag"); 
		String flag_id = request.getParameter("flag_id"); 
		
		if(!cutil.IsNumber(flag_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(flag_id);
		
		choice.setChoiceType(Integer.valueOf(choice_type));
		choice.setChoiceInfo(choice_info);
		choice.setChoiceScore(Integer.valueOf(choice_score));
		choice.setChoiceSub(choice_sub);
		
		if(choice_flag.equals("problem")) {
			choice.setProblemId(fid);
			choice.setTemplateId(0);
		}
		else {
			choice.setTemplateId(fid);
			choice.setProblemId(0);
		}
		
		flag = ChoiceService.AddChoice(choice);
		
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
	
	//添加一堆选项
	public void AddMutiplyChoice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String ChoiceInfo = request.getParameter("choice_info"); 
		String problem_id = request.getParameter("problem_id");
		
		if(!cutil.IsNumber(problem_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		JSONArray ja = JSONArray.fromObject(ChoiceInfo);
		List<Choice> clist = new ArrayList<Choice>();
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = new JSONObject();
			
			String choice_type = jo.getString("choice_type"); 
			String choice_info = jo.getString("choice_info"); 
			String choice_sub = jo.getString("choice_sub"); 
			String choice_score = jo.getString("choice_score");  
			
			Choice ch = new Choice();
			choice.setChoiceType(Integer.valueOf(choice_type));
			choice.setChoiceInfo(choice_info);
			choice.setChoiceScore(Integer.valueOf(choice_score));
			choice.setChoiceSub(choice_sub);
			choice.setProblemId(pid);
			choice.setTemplateId(0);
			
			clist.add(ch);
		}
		
		ChoiceService.AddMutiplyChoice(clist);
		
		JSONObject jo = new JSONObject();
		jo.put("result", "Success");
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;

	}
	
	//通过题目查询选项
	public void QueryChoiceByProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
	
		String problem_id = request.getParameter("problem_id");
		
		if(!cutil.IsNumber(problem_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "Fail");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		List<Choice> clist = ChoiceService.QueryChoiceByProblem(pid);
		
		JSONObject jo = new JSONObject();
		if (clist.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("Array", "null");
		} 
		else {
			JSONArray ja = JSONArray.fromObject(clist);
			jo.put("Count", clist.size());
			jo.put("rows", 1);
			jo.put("PageSize", clist.size());
			jo.put("Array", ja.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;

	}

	//更新选项
	public void UpdateChoice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
	
		String choice_id = request.getParameter("choice_id");
		String choice_name = request.getParameter("choice_name");
		String choice_score = request.getParameter("choice_score");
		
		int cid = Integer.valueOf(choice_id);
		int score = Integer.valueOf(choice_score);
		
		choice = ChoiceService.QueryChoice(cid);
		
		boolean flag = true;
		
		choice.setChoiceInfo(choice_name);
		choice.setChoiceScore(score);
		
		flag = ChoiceService.UpdateChoice(choice);
		
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
