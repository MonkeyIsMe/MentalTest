package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Choice;
import CSU.Mental.Model.Template;
import CSU.Mental.Service.ChoiceService;
import CSU.Mental.Service.TemplateService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TemplateAction extends ActionSupport{
	
	private CommonUtils cutil = new CommonUtils();
	private TemplateService TemplateService;
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
	
	//增加一个模板
	public void AddTemplate() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String ChoiceInfo = request.getParameter("choice_info");
		String template_name = request.getParameter("template_name");
		
		template.setTemplateName(template_name);
		
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
		
		ChoiceService.AddMutiplyChoice(clist);
		
		out.println("Success");
		out.flush();
		out.close();
		
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
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int tid = Integer.valueOf(template_id);
		
		template = TemplateService.QueryTemplate(tid);
		
		if(template == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		flag = TemplateService.DeleteTemplate(template);
		
		List<Choice> del_list = ChoiceService.QueryChoiceByTemplate(tid);
		ChoiceService.DeleteMutiplyChoice(del_list);
		
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
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int tid = Integer.valueOf(template_id);
		
		template = TemplateService.QueryTemplate(tid);
		
		if(template == null) {
			out.println("Fail");
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
	
	//查询模板总数
	public void CountTemplate() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int count = TemplateService.CountTemplate();
		
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
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", count);
			jo.put("rows", 1);
			jo.put("PageSize", count);
			jo.put("Array", TemplateList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
}
