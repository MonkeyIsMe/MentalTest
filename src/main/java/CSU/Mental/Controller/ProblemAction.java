package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.gson.annotations.JsonAdapter;
import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Choice;
import CSU.Mental.Model.FactorProblem;
import CSU.Mental.Model.Problem;
import CSU.Mental.Service.ChoiceService;
import CSU.Mental.Service.FactorProblemService;
import CSU.Mental.Service.ProblemService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProblemAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private ProblemService ProblemService;
	private FactorProblemService FactorProblemService;
	private ChoiceService ChoiceService;
	private Problem problem = new Problem();
	
	public ProblemService getProblemService() {
		return ProblemService;
	}

	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}

	public ChoiceService getChoiceService() {
		return ChoiceService;
	}

	public void setChoiceService(ChoiceService choiceService) {
		ChoiceService = choiceService;
	}

	public FactorProblemService getFactorProblemService() {
		return FactorProblemService;
	}

	public void setFactorProblemService(FactorProblemService factorProblemService) {
		FactorProblemService = factorProblemService;
	}

	//删除一个问题
	public void DeleteProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String problem_id = request.getParameter("problem_id");
		//System.out.println(problem_id);
		if(!cutil.IsNumber(problem_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		problem = ProblemService.QueryProblem(pid);
		
		flag = ProblemService.DeleteProblem(problem);
		
		if(flag) {
			List<Choice> ChoiceList = ChoiceService.QueryChoiceByProblem(pid);
			if(ChoiceList.size() == 0) {
				out.println("Success");
				out.flush();
				out.close();
				return;
			}
			ChoiceService.DeleteMutiplyChoice(ChoiceList);
		}
		
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
	
	public void QuerySingleProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		if(!cutil.IsNumber(problem_id)) {
			
			JSONObject jos = new JSONObject();
			jos.put("ProblemInfo", "null");
			jos.put("ChoiceInfo", "null");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		problem = ProblemService.QueryProblem(pid);
		
		if(problem == null) {
			
			JSONObject jos = new JSONObject();
			jos.put("ProblemInfo", "null");
			jos.put("ChoiceInfo", "null");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		List<Choice> clist = ChoiceService.QueryChoiceByProblem(pid);
		
		JSONObject jo = new JSONObject();
		jo.put("ProblemInfo", problem.toString());
		jo.put("ChoiceInfo", clist.toString());
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//添加一个问题
	public void AddProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String scale_id = request.getParameter("scale_id");
		String problem_name = request.getParameter("problem_name");
		String problem_type = request.getParameter("problem_type");
		String template_id = request.getParameter("template_id");
		String problem_flag = request.getParameter("problem_flag");
		String problem_number = request.getParameter("problem_number");
		String ChoiceInfo = request.getParameter("choice_info"); //选项信息
		
		if(!cutil.IsNumber(scale_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(template_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int tid = Integer.valueOf(template_id);
		int sid = Integer.valueOf(scale_id);
		
		problem.setProblemFlag(Integer.valueOf(problem_flag));
		problem.setProblemName(problem_name);
		problem.setProblemFlag(tid);
		problem.setScaleId(sid);
		problem.setProblemType(problem_type);
		problem.setProblemNumber(problem_number);
		
		int pid = ProblemService.AddProblem(problem);
		if(tid == 0){
			//处理选项
			List<Choice> clist = new ArrayList<Choice>();
			JSONArray cja = JSONArray.fromObject(ChoiceInfo);
			for(int i = 0; i < cja.size(); i ++) {
				JSONObject cjo = new JSONObject();
				String choice_info = cjo.getString("choice_info");
				String choice_sub = cjo.getString("choice_sub");
				String choice_score = cjo.getString("choice_score");
				String choice_type = cjo.getString("choice_type");
				
				Choice ch = new Choice();
				ch.setChoiceInfo(choice_info);
				ch.setTemplateId(0);
				ch.setChoiceSub(choice_sub);
				ch.setChoiceScore(Integer.valueOf(choice_score));
				ch.setChoiceType(Integer.valueOf(choice_type));
				ch.setProblemId(pid);
				
				clist.add(ch); 
			}
			
			ChoiceService.AddMutiplyChoice(clist);
		}
		
		out.println("Success");
		out.flush();
		out.close();
		return;
	}
	
	//更新一个问题题面
	public void UpdateProblemInfo() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String problem_id = request.getParameter("problem_id");
		String problem_name = request.getParameter("problem_name");
		String problem_type = request.getParameter("problem_type");
		String template_id = request.getParameter("template_id");
		
		if(!cutil.IsNumber(problem_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(template_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int tid = Integer.valueOf(template_id);
		int pid = Integer.valueOf(problem_id);
		
		problem = ProblemService.QueryProblem(pid);
		
		if(problem == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		problem.setProblemName(problem_name);
		problem.setProblemType(problem_type);
		problem.setTemplateId(tid);
		
		flag = ProblemService.UpdateProblem(problem);
		
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
	
	//添加量表题目
	public void AddScaleProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String ProblemInfo = request.getParameter("problem_info");
		String scale_id = request.getParameter("scale_id");
		
		int sid = Integer.valueOf(scale_id);
		
		//处理题目
		JSONArray ja = JSONArray.fromObject(ProblemInfo);
		List<Choice> clist = new ArrayList<Choice>();
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			
			Problem pro = new Problem();
			String problem_name = jo.getString("problem_name"); //题目名字
			String problem_type = jo.getString("problem_type"); //题目类型
 			String template_id = jo.getString("template_id");  //模板编号
 			String problem_flag = jo.getString("problem_flag");  //模板编号
 			String problem_number = jo.getString("problem_number");  //模板编号
			String ChoiceInfo = jo.getString("choice_info"); //选项信息
			//String factor_info = jo.getString("factor_info"); //因子信息
			
			int temp = Integer.valueOf(template_id);
			pro.setScaleId(sid);
			pro.setTemplateId(temp);
			pro.setProblemType(problem_type);
			pro.setProblemName(problem_name);
			pro.setProblemFlag(Integer.valueOf(problem_flag));
			pro.setProblemNumber(problem_number);
			int pid = ProblemService.AddProblem(pro);
			
/*			List<FactorProblem> fplist = new ArrayList<FactorProblem>();
			JSONArray fja = JSONArray.fromObject(factor_info);
			for(int j = 0; j < fja.size(); j ++) {
				JSONObject fjo = fja.getJSONObject(j);
				String factor_id = fjo.getString("factor_id");
				int fid = Integer.valueOf(factor_id);
				FactorProblem fp = new FactorProblem();
				fp.setFactorId(fid);
				fp.setProblemId(pid);
				fplist.add(fp);
			}
			
			FactorProblemService.AddMutiplyFactorProblem(fplist);*/
			
			//没用模板
			if(temp == 0) {
				JSONArray cja = JSONArray.fromObject(ChoiceInfo);
				for(int j = 0; j < cja.size(); j ++) {
					
					JSONObject cjo = cja.getJSONObject(j);
					
					String choice_info = cjo.getString("choice_info");
					String choice_sub = cjo.getString("choice_sub");
					String choice_score = cjo.getString("choice_score");
					String choice_type = cjo.getString("choice_type");
					
					Choice ch = new Choice();
					ch.setChoiceInfo(choice_info);
					ch.setTemplateId(0);
					ch.setChoiceSub(choice_sub);
					ch.setChoiceScore(Integer.valueOf(choice_score));
					ch.setChoiceType(Integer.valueOf(choice_type));
					ch.setProblemId(pid);
					
					clist.add(ch);
				}
			}
			
		}
		
		ChoiceService.AddMutiplyChoice(clist);
		
		out.println("Success");
		out.flush();
		out.close();
		return;
	}
}
