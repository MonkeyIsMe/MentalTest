package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Patient;
import CSU.Mental.Model.Record;
import CSU.Mental.Model.Scale;
import CSU.Mental.Service.PatientService;
import CSU.Mental.Service.RecordService;
import CSU.Mental.Service.ScaleService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RecordAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private RecordService RecordService;
	private PatientService PatientService;
	private ScaleService ScaleService;
	private Scale scale = new Scale();
	private Patient patient = new Patient();
	private Record record = new Record();
	
	
	public PatientService getPatientService() {
		return PatientService;
	}

	public void setPatientService(PatientService patientService) {
		PatientService = patientService;
	}

	public ScaleService getScaleService() {
		return ScaleService;
	}

	public void setScaleService(ScaleService scaleService) {
		ScaleService = scaleService;
	}

	public RecordService getRecordService() {
		return RecordService;
	}

	public void setRecordService(RecordService recordService) {
		RecordService = recordService;
	}


	//查询记录总数
	public void CountRecord() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		JSONObject jo = new JSONObject();
		
		String record_flag = request.getParameter("record_flag");
		String flag_id = request.getParameter("flag_id");
		
		int count = 0;
		int fid = 0;
		
		if(record_flag.equals("user")) {
			if(cutil.IsNumber(flag_id)) {
				fid = Integer.valueOf(flag_id);
				count = RecordService.CountRecorByUserd(fid);
			}
		}
		else if(record_flag.equals("scale")) {
			if(cutil.IsNumber(flag_id)) {
				fid = Integer.valueOf(flag_id);
				count = RecordService.CountRecordByScale(fid);
			}
		}
		else if(record_flag.equals("patient")) {
			if(cutil.IsNumber(flag_id)) {
				fid = Integer.valueOf(flag_id);
				count = RecordService.CountRecordByPatient(fid);
			}
		}
		else {
			count = RecordService.CountRecord();
		}
		
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	
	//分页查询记录数
	public void QueryRecordPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String record_flag = request.getParameter("record_flag");
		String flag_id = request.getParameter("flag_id");
		
		int count = 0;
		int fid = 0;
		List<Record> RecordList = null;
		
		
		int rows = 1;
		int PageSize = 5;

		if (!(page == null || page == "" || page.equals("")) && cutil.IsNumber(page)) {
			rows = Integer.valueOf(page);
		}
		if (!(size == null || size == "" || size.equals("")) && cutil.IsNumber(size)) {
			PageSize = Integer.valueOf(size);
		}
		
		if(record_flag.equals("user")) {
			if(cutil.IsNumber(flag_id)) {
				fid = Integer.valueOf(flag_id);
				count = RecordService.CountRecorByUserd(fid);
				RecordList = RecordService.QueryRecordByUserPageSize(fid, rows, PageSize);
			}
		}
		else if(record_flag.equals("scale")) {
			if(cutil.IsNumber(flag_id)) {
				fid = Integer.valueOf(flag_id);
				count = RecordService.CountRecordByScale(fid);
				RecordList = RecordService.QueryRecordByScalePageSize(fid, rows, PageSize);
			}
		}
		else if(record_flag.equals("patient")) {
			if(cutil.IsNumber(flag_id)) {
				fid = Integer.valueOf(flag_id);
				count = RecordService.CountRecordByPatient(fid);
				RecordList = RecordService.QueryRecordByPatientPageSize(fid, rows, PageSize);
			}
		}
		else {
			count = RecordService.CountRecord();
			RecordList = RecordService.QueryRecordPageSize(rows, PageSize);
		}
		
		JSONArray ja = new JSONArray();
		for(Record r : RecordList) {
			JSONObject jo = new JSONObject();
			int pid = r.getPatientId();
			int sid = r.getScaleId();
			
			jo.put("PatientId", pid);
			jo.put("ScaleId", sid);
			
			patient = PatientService.QueryPatient(pid);
			scale = ScaleService.QueryScale(sid);
			jo.put("RecordFator", r.getRecordFactor());
			jo.put("RecordId", r.getRecordId());
			jo.put("RecordInfo", r.getRecordInfo());
			jo.put("RecordProblem", r.getRecordProblem());
			jo.put("RecordTime", r.getRecordTime());
			jo.put("UserId", r.getUserId());
			jo.put("PatientName", patient.getPatientName());
			jo.put("ScaleName", scale.getScaleName());
			ja.add(jo);
		}
		
		JSONObject jo = new JSONObject();
		if(RecordList.size() == 0) {
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
			jo.put("msg", "Accept");
			jo.put("code", 0);
			jo.put("PageSize", PageSize);
			jo.put("Array", ja.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}

	//删除一个记录
	public void DeleteRecord() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String record_id = request.getParameter("record_id");
		
		if(!cutil.IsNumber(record_id)) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoRecordId");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		int rid = Integer.valueOf(record_id);
		
		record = RecordService.QueryRecord(rid);
		
		if(record == null) {
			JSONObject jos = new JSONObject();
			jos.put("result", "NoRecord");
			out.println(jos.toString());
			out.flush();
			out.close();
			return;
		}
		
		flag = RecordService.DeleteRecord(record);
		
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
