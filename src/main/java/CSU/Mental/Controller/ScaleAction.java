package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Choice;
import CSU.Mental.Model.Factor;
import CSU.Mental.Model.FactorProblem;
import CSU.Mental.Model.Patient;
import CSU.Mental.Model.Problem;
import CSU.Mental.Model.Record;
import CSU.Mental.Model.Reference;
import CSU.Mental.Model.Scale;
import CSU.Mental.Service.ChoiceService;
import CSU.Mental.Service.FactorProblemService;
import CSU.Mental.Service.FactorService;
import CSU.Mental.Service.PatientService;
import CSU.Mental.Service.ProblemService;
import CSU.Mental.Service.RecordService;
import CSU.Mental.Service.ReferenceService;
import CSU.Mental.Service.ScaleService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ScaleAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private ScaleService ScaleService;
	private FactorService FactorService;
	private ReferenceService ReferenceService;
	private ProblemService ProblemService;
	private PatientService PatientService;
	private RecordService RecordService;
	private ChoiceService ChoiceService;
	private FactorProblemService FactorProblemService;
	private Scale scale = new Scale();
	private Factor factor = new Factor();
	private Patient patient = new Patient();
	
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

	public ScaleService getScaleService() {
		return ScaleService;
	}
	
	public void setScaleService(ScaleService scaleService) {
		ScaleService = scaleService;
	}
	
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
	public ProblemService getProblemService() {
		return ProblemService;
	}
	
	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}

	public RecordService getRecordService() {
		return RecordService;
	}

	public void setRecordService(RecordService recordService) {
		RecordService = recordService;
	}
	
	public PatientService getPatientService() {
		return PatientService;
	}

	public void setPatientService(PatientService patientService) {
		PatientService = patientService;
	}
	
	//更新使用次数
	public void UpdateScaleNumber() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String scale_id = request.getParameter("scale_id");
		
		if(!cutil.IsNumber(scale_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoScaleId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int sid = Integer.valueOf(scale_id);
		
		scale = ScaleService.QueryScale(sid);
		
		if(scale == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoScale");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int num = scale.getScaleNumber();
		num ++;
		scale.setScaleNumber(num);
		
		flag = ScaleService.UpdtaeScale(scale);
		
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
	
	//做量表题
	public void DoScaleTest() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String scale_id = request.getParameter("scale_id");
		String choice_info = request.getParameter("choice_info");
		String patient_id = request.getParameter("patient_id");
		//System.out.println(choice_info);
		if(!cutil.IsNumber(scale_id)) {
			out.println("NoScaleId");
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(patient_id)) {
			out.println("NoPatientId");
			out.flush();
			out.close();
			return;
		}
		
		int PatientId = Integer.valueOf(patient_id);
		int sid = Integer.valueOf(scale_id);
		
		patient = PatientService.QueryPatient(PatientId);
		scale = ScaleService.QueryScale(sid);
		
		JSONArray ja = JSONArray.fromObject(choice_info);
		
		Map<Integer,Integer> mp = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < ja.size(); i ++) {
			
			JSONObject jo = ja.getJSONObject(i);
			
			String ProblemId = jo.getString("ProblemId");
			String Score = jo.getString("Score");
			
			int pid = Integer.valueOf(ProblemId);
			int score = Integer.valueOf(Score);
			
			List<FactorProblem> fplist = FactorProblemService.QueryFactorProblemByProblem(pid);
			for(FactorProblem fp : fplist) {
				int fid = fp.getFactorId();
				if(mp.get(fid) == null) {
					mp.put(fid, score);
				}
				else {
					int grade = mp.get(fid);
					grade += score;
					mp.put(fid, grade);
				}
			}
		}
		
		JSONObject jo = new JSONObject();
		
		Set<Integer> keySet = mp.keySet();
		Iterator<Integer> it = keySet.iterator();
		
		JSONArray ans_ja = new JSONArray();
		JSONArray fac_ja = new JSONArray();
		
        while(it.hasNext()){     
        	
            //得到每一个factorid
            Integer key = it.next();
            
            //通过factorid获取对应的分数
            Integer value = mp.get(key);
            
            String answer = null;
            List<Reference> rlist = ReferenceService.QueryReferenceByFactor(key);
            
            factor = FactorService.QueryFactor(key);
            
            //因子得分
            JSONObject fac_jo = new JSONObject();
            fac_jo.put("FactorName", factor.getFactorName());
            fac_jo.put("FactorScore", value);
            
            fac_ja.add(fac_jo);
            
/*            System.out.println(factor.getFactorName() + "  " + key + "  " + value);
            for(Reference r : rlist) {
            	System.out.println(r.toString());
            }*/
            //查找答案
            int begin = 0;
            int end = 0;
            for(Reference refer : rlist) {
            	begin = refer.getReferenceBeginScore();
            	end = refer.getReferenceEndScore();
            	if(value >= begin && value <= end) {
            		answer = refer.getReferenceSuggestion();
            		break;
            	}
            }
            jo.put("FactorScore", value);
            jo.put("ReferenceInfo", answer);
            jo.put("ScoreBegin", begin);
            jo.put("ScoreEnd", end);
            jo.put("FactorName", factor.getFactorName());
            jo.put("FactorInfo", factor.getFactorInfo());
            ans_ja.add(jo);
        }
        
        //保存一份记录
        Record record = new Record();
        record.setPatientId(PatientId);
        record.setRecordInfo(ans_ja.toString());
        record.setScaleId(sid);
        record.setRecordTime(cutil.GetNowDate());
        record.setRecordProblem(choice_info);
        record.setRecordFactor(fac_ja.toString());
        
        int RecordId = RecordService.AddRecord(record);
        
        
        JSONObject result = new JSONObject();
        result.put("ScaleInfo", scale.toString());
        result.put("SuggestionInfo", ans_ja.toString());
        result.put("FactorInfo", fac_ja.toString());
        result.put("RecordId", RecordId);
        if(patient == null) {
        	result.put("PatientInfo", "");
        }
        else result.put("PatientInfo", patient.toString());
        
		out.println(result.toString());
		out.flush();
		out.close();
		return;
	}
	
	//查询量表信息
	public void QueryScaleInfo() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String scale_id = request.getParameter("scale_id");

		if(!cutil.IsNumber(scale_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoScaleId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}

		int sid = Integer.valueOf(scale_id);
		//System.out.println(sid);
		JSONArray result = new JSONArray();
		
		List<Problem> ProblemlList = ProblemService.QueryProblemByScale(sid);
		for(Problem p : ProblemlList) {
			
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			
			int pid = p.getProblemId();
			int tid = p.getTemplateId();
			String name = p.getProblemName();
			String type = p.getProblemType();
			int id = p.getProblemId();
			
			List<Choice> ChoiceList = ChoiceService.QueryChoiceByProblem(pid);
			if(ChoiceList.size() == 0) {
				//找模板选项
				List<Choice> cList = ChoiceService.QueryChoiceByTemplate(tid);
				ja = JSONArray.fromObject(cList);
			}
			else {
				ja = JSONArray.fromObject(ChoiceList);
			}
			
			jo.put("ProblemName", name);
			jo.put("ProblemType", type);
			jo.put("ProblemId", id);
			jo.put("ChoiceInfo", ja.toString());
			
			result.add(jo);
		}
		
		JSONObject jo = new JSONObject();
		jo.put("result", result.toString());
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
	
	//查询单一量表
	public void QuerySingleScale() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String scale_id = request.getParameter("scale_id");
		
		if(!cutil.IsNumber(scale_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoScaleId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int sid = Integer.valueOf(scale_id);
		
		scale = ScaleService.QueryScale(sid);
		
		out.println(scale.toString());
		out.flush();
		out.close();
		return;
	}
	
	//更新量表信息
	public void UpdateScale() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String scale_id = request.getParameter("scale_id");
		String scale_name = request.getParameter("scale_name");
		String scale_info = request.getParameter("scale_info");
		String scale_guide = request.getParameter("scale_guide");
		String sk_id = request.getParameter("sk_id");
		String fk_id = request.getParameter("fk_id");
		boolean flag = true;
		
		if(!cutil.IsNumber(scale_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoScaleId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(sk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoSkindId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(fk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoFkindId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fkid = Integer.valueOf(fk_id);
		int sid = Integer.valueOf(scale_id);
		int skid = Integer.valueOf(sk_id);
		
		scale = ScaleService.QueryScale(sid);
		
		if(scale == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		scale.setFkinId(fkid);
		scale.setSkinId(skid);
		scale.setScaleGuide(scale_guide);
		scale.setScaleName(scale_name);
		scale.setScaleInfo(scale_info);
		scale.setScaleUpdateTime(cutil.GetNowDate());
		
		flag = ScaleService.UpdtaeScale(scale);
		
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
	
	//添加量表
	public void AddScale() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String scale_name = request.getParameter("scale_name");
		String scale_info = request.getParameter("scale_info");
		String scale_guide = request.getParameter("scale_guide");
		String sk_id = request.getParameter("sk_id");
		String fk_id = request.getParameter("fk_id");
		
		if(!cutil.IsNumber(sk_id)) {
			JSONObject jo = new JSONObject();
			jo.put("ScaleId", 0);
			
			out.println(jo.toString());
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(fk_id)) {
			JSONObject jo = new JSONObject();
			jo.put("ScaleId", 0);
			
			out.println(jo.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fkid = Integer.valueOf(fk_id);
		int skid = Integer.valueOf(sk_id);
		
		scale.setFkinId(fkid);
		scale.setScaleGuide(scale_guide);
		scale.setSkinId(skid);
		scale.setScaleName(scale_name);
		scale.setScaleInfo(scale_info);
		scale.setScaleNumber(0);
		scale.setScaleUpdateTime(cutil.GetNowDate());
		
		int sid = ScaleService.AddScale(scale);
		
		
		JSONObject jo = new JSONObject();
		jo.put("ScaleId", sid);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
	
	//分页查询量表
	public void QueryScalePageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int rows = 1;
		int PageSize = 5;

		if (!(page == null || page == "" || page.equals("")) && cutil.IsNumber(page)) {
			rows = Integer.valueOf(page);
		}
		if (!(size == null || size == "" || size.equals("")) && cutil.IsNumber(size)) {
			PageSize = Integer.valueOf(size);
		}
		
		int count = ScaleService.CountScale();
		List<Scale> ScaleList = ScaleService.QueryScalePageSize(PageSize, rows);
		
		JSONObject jo = new JSONObject();
		if(ScaleList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("msg", "NoData");
			jo.put("code", 0);
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", count);
			jo.put("rows", rows);
			jo.put("PageSize", PageSize);
			jo.put("msg", "Accept");
			jo.put("code", 0);
			jo.put("Array", ScaleList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
	
	//查询量表总数
	public void CountScale() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String flag_id = request.getParameter("flag_id");
		String scale_flag = request.getParameter("scale_flag");
		
		if(!cutil.IsNumber(flag_id)) {
			
			JSONObject jos = new JSONObject();
			jos.put("Count", "0");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fid = Integer.valueOf(flag_id);
		
		int count = 0;
		
		if(scale_flag.equals("skind")) {
			count = ScaleService.CountScaleBySkind(fid);
		}
		else if(scale_flag.equals("fkind")) {
			count = ScaleService.CountScaleByFkind(fid);
		}
		else {
			count = ScaleService.CountScale();
		}
		
		JSONObject jo = new JSONObject();
		
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}
	
	//根据大类小类分页查询量表
	public void QueryScaleByFkindSkindPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String fk_id = request.getParameter("fk_id");
		String sk_id = request.getParameter("sk_id");
		
		int rows = 1;
		int PageSize = 5;

		if (!(page == null || page == "" || page.equals("")) && cutil.IsNumber(page)) {
			rows = Integer.valueOf(page);
		}
		if (!(size == null || size == "" || size.equals("")) && cutil.IsNumber(size)) {
			PageSize = Integer.valueOf(size);
		}
		
		if(!cutil.IsNumber(fk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("Count", "0");
			jos.put("rows", "0");
			jos.put("msg", "NoFkindId");
			jos.put("code", 0);
			jos.put("PageSize", "0");
			jos.put("Array", "null");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(sk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("Count", "0");
			jos.put("rows", "0");
			jos.put("msg", "NoSkindId");
			jos.put("code", 0);
			jos.put("PageSize", "0");
			jos.put("Array", "null");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fkid = Integer.valueOf(fk_id);
		int skid = Integer.valueOf(sk_id);
		
		int count = ScaleService.CountScaleBySkindFkind(skid, fkid);
		List<Scale> ScaleList = ScaleService.QueryScaleBySkindFkindPageSize(PageSize, rows, skid, fkid);
		
		JSONObject jo = new JSONObject();
		if(ScaleList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("msg", "NoData");
			jo.put("code", 0);
			jo.put("PageSize", "0");
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", count);
			jo.put("rows", rows);
			jo.put("msg", "Accept");
			jo.put("code", 0);
			jo.put("PageSize", PageSize);
			jo.put("Array", ScaleList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//根据大类小类查询总数
	public void CountScaleByFkindSkind() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String fk_id = request.getParameter("fk_id");
		String sk_id = request.getParameter("sk_id");

		
		if(!cutil.IsNumber(fk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("Count", "0");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		if(!cutil.IsNumber(sk_id)) {
			JSONObject jos = new JSONObject();
			jos.put("Count", "0");
			
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int fkid = Integer.valueOf(fk_id);
		int skid = Integer.valueOf(sk_id);
		
		int count = ScaleService.CountScaleBySkindFkind(skid, fkid);
		
		JSONObject jo = new JSONObject();
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}

	//删除量表
	public void DeleteScale() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String scale_id = request.getParameter("scale_id");
		
		//boolean flag = true;
		
		if(!cutil.IsNumber(scale_id)) {
			JSONObject jo = new JSONObject();
			jo.put("result", "NoScaleId");
			
			out.println(jo.toString());
			out.flush();
			out.close();
			return;
		}
		
		
		int sid = Integer.valueOf(scale_id);
		
		scale = ScaleService.QueryScale(sid);
		
		ScaleService.DeleteScale(scale);
		
		//和一个量表相关的类; 
		List<Problem> plist = ProblemService.QueryProblemByScale(sid);
		List<Factor> flist = FactorService.QueryFactorByScale(sid);
		
		for(Problem pro : plist) {
			int pid = pro.getProblemId();
			List<Choice> clist = ChoiceService.QueryChoiceByProblem(pid);
			ChoiceService.DeleteMutiplyChoice(clist);
		}
		
		ProblemService.DeleteMutiplyProblem(plist);
		
		for(Factor fac : flist) {
			int fid = fac.getFactorId();
			List<Reference> rlist = ReferenceService.QueryReferenceByFactor(fid);
			ReferenceService.DeleteMutiplyReference(rlist);
		}
		
		FactorService.DeleteMutiplyFactor(flist);
		
		JSONObject jo = new JSONObject();
		jo.put("result", "Success");
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}

	//模糊分页查询量表
	public void VagueScalePageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String scale_name = request.getParameter("scale_name");
		
		int rows = 1;
		int PageSize = 5;

		if (!(page == null || page == "" || page.equals("")) && cutil.IsNumber(page)) {
			rows = Integer.valueOf(page);
		}
		if (!(size == null || size == "" || size.equals("")) && cutil.IsNumber(size)) {
			PageSize = Integer.valueOf(size);
		}
		
		int count = ScaleService.CountVagueScale(scale_name);
		List<Scale> ScaleList = ScaleService.VagueScalePageSize(PageSize, rows, scale_name);
		
		JSONObject jo = new JSONObject();
		if(ScaleList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("msg", "NoData");
			jo.put("code", 0);
			jo.put("PageSize", "0");
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", count);
			jo.put("rows", rows);
			jo.put("msg", "Accept");
			jo.put("code", 0);
			jo.put("PageSize", PageSize);
			jo.put("Array", ScaleList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//模糊查询量表总数
	public void CountVagueScale() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String scale_name = request.getParameter("scale_name");
		
		int count = ScaleService.CountVagueScale(scale_name);
		
		JSONObject jo = new JSONObject();
		
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
}
