package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Choice;
import CSU.Mental.Model.Problem;
import CSU.Mental.Model.Template;
import CSU.Mental.Service.ChoiceService;
import CSU.Mental.Service.ProblemService;
import CSU.Mental.Service.TemplateService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TemplateAction extends ActionSupport{
	
	private CommonUtils cutil = new CommonUtils();
	private TemplateService TemplateService;
	private ProblemService ProblemService;
	private ChoiceService ChoiceService;
	private Template template = new Template();
	
	public ChoiceService getChoiceService() {
		return ChoiceService;
	}
	public void setChoiceService(ChoiceService choiceService) {
		ChoiceService = choiceService;
	}
	public TemplateService getTemplateService() {
		return TemplateService;
	}
	public void setTemplateService(TemplateService templateService) {
		TemplateService = templateService;
	}
	
	public ProblemService getProblemService() {
		return ProblemService;
	}
	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}
	
	//增加一个模板
	public void AddTemplate() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String ChoiceInfo = request.getParameter("choice_info");
		String template_name = request.getParameter("template_name");
		//System.out.println(ChoiceInfo);
		template.setTemplateName(template_name);
		template.setTemplateNumber(0);
		
		int tid = TemplateService.AddTemplate(template);
		
		List<Choice> clist = new ArrayList<Choice>();
		JSONArray ja = JSONArray.fromObject(ChoiceInfo);
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);

			String choice_info = jo.getString("choice_info");
			String choice_sub = jo.getString("choice_sub");
			String choice_score = jo.getString("choice_score");
			String choice_type = jo.getString("choice_type");
			
			Choice ch = new Choice();
			ch.setChoiceInfo(choice_info);
			ch.setTemplateId(tid);
			ch.setChoiceSub(choice_sub);
			ch.setChoiceScore(Integer.valueOf(choice_score));
			ch.setChoiceType(Integer.valueOf(choice_type));
			ch.setProblemId(0);
			
			clist.add(ch); 
		}
		//System.out.println(clist.size());
		ChoiceService.AddMutiplyChoice(clist);
		
		JSONObject jos = new JSONObject();
		jos.put("result", "Success");
		out.println(jos.toString());
		out.flush();
		out.close();
		return;
		
	}
	
	//删除一个模板
	public void DeleteTemplate() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String template_id = request.getParameter("template_id");
		
		if(!cutil.IsNumber(template_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoTemplateId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int tid = Integer.valueOf(template_id);
		
		template = TemplateService.QueryTemplate(tid);
		
		if(template == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoTemplate");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		flag = TemplateService.DeleteTemplate(template);
		
		//处理选项
		List<Choice> del_clist = ChoiceService.QueryChoiceByTemplate(tid);
		ChoiceService.DeleteMutiplyChoice(del_clist);
		
		//处理题目
		List<Problem> del_plist = ProblemService.QueryProblemByTemplate(tid);
		List<Problem> plist = new ArrayList<Problem>();
		for(Problem pro : del_plist) {
			pro.setTemplateId(0);
			plist.add(pro);
		}
		
		ProblemService.UpdateMutiplyProblem(plist);
		
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
	
	//更新一个模板
	public void UpdateTemplate() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String template_id = request.getParameter("template_id");
		String template_name = request.getParameter("template_name");
		String ChoiceInfo = request.getParameter("choice_info");
		
		if(!cutil.IsNumber(template_id)) {
			out.println("NoTemplateId");
			out.flush();
			out.close();
			return;
		}
		
		int tid = Integer.valueOf(template_id);
		
		template = TemplateService.QueryTemplate(tid);
		
		if(template == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoTemplate");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		List<Choice> del_list = ChoiceService.QueryChoiceByTemplate(tid);
		ChoiceService.DeleteMutiplyChoice(del_list);
		
		List<Choice> clist = new ArrayList<Choice>();
		JSONArray ja = JSONArray.fromObject(ChoiceInfo);
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);

			String choice_info = jo.getString("choice_info");
			String choice_sub = jo.getString("choice_sub");
			String choice_score = jo.getString("choice_score");
			String choice_type = jo.getString("choice_type");
			
			Choice ch = new Choice();
			ch.setChoiceInfo(choice_info);
			ch.setTemplateId(tid);
			ch.setChoiceSub(choice_sub);
			ch.setChoiceScore(Integer.valueOf(choice_score));
			ch.setChoiceType(Integer.valueOf(choice_type));
			ch.setProblemId(0);
			
			clist.add(ch); 
		}
		
		ChoiceService.AddMutiplyChoice(clist);
		
		template.setTemplateName(template_name);
		
		flag = TemplateService.UpdateTemplate(template);
		
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
	
	//查询模板总数
	public void CountTemplate() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String template_name = request.getParameter("template_name");
		
		int count = 0;
		if(template_name == "" || template_name.equals("")) {
			count = TemplateService.CountTemplate();
		}
		else count = TemplateService.CountVagueTemplate(template_name);
		
		JSONObject jo = new JSONObject();
		
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//查询所有模板
	public void QueryAllTemplate() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		List<Template> TemplateList = TemplateService.QueryAllTemplate();
		int count = TemplateService.CountTemplate();
		
		JSONObject jo = new JSONObject();
		if(TemplateList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("msg", "NoData");
			jo.put("code", 0);
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", count);
			jo.put("rows", 1);
			jo.put("msg", "Accept");
			jo.put("code", 0);
			jo.put("PageSize", count);
			jo.put("Array", TemplateList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}

	
	//分页查询模板
	public void QueryTemplatePageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String template_name = request.getParameter("template_name");
		//System.out.println(template_name);
		int rows = 1;
		int PageSize = 5;

		if (!(page == null || page == "" || page.equals("")) && cutil.IsNumber(page)) {
			rows = Integer.valueOf(page);
		}
		if (!(size == null || size == "" || size.equals("")) && cutil.IsNumber(size)) {
			PageSize = Integer.valueOf(size);
		}
		
		List<Template> tlist = null;
		if(template_name == "" || template_name.equals("")) {
			tlist = TemplateService.QueryTemplatePageSize(PageSize, rows);
		}
		else tlist = TemplateService.VagueTemplatePageSize(template_name, PageSize, rows);
		
		JSONArray ja = new JSONArray();
		
		for(Template temp : tlist) {
			int tid = temp.getTemplateId();
			List<Choice> clist = ChoiceService.QueryChoiceByTemplate(tid);
			JSONObject jo = new JSONObject();
			jo.put("TemplateId", tid);
			jo.put("TemplateName", temp.getTemplateName());
			jo.put("ChoiceList", clist.toString());
			ja.add(jo);
 		}
		
		JSONObject result = new JSONObject();
		if(ja.size() == 0) {
			result.put("Count", "0");
			result.put("rows", "0");
			result.put("msg", "NoData");
			result.put("code", 0);
			result.put("PageSize", "0");
			result.put("Array", "null");
		}
		else {
			result.put("Count", ja.size());
			result.put("rows", rows);
			result.put("msg", "Accept");
			result.put("code", 0);
			result.put("PageSize", PageSize);
			result.put("Array", ja.toString());
		}
		
		out.println(result.toString());
		out.flush();
		out.close();
		return;
		
	}
}
