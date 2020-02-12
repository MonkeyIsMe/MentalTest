package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Patient;
import CSU.Mental.Service.PatientService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONObject;

public class PatientAction extends ActionSupport{
	
	private PatientService PatientService;
	private Patient patient = new Patient();
	private CommonUtils cutil = new CommonUtils();
	
	public PatientService getPatientService() {
		return PatientService;
	}

	public void setPatientService(PatientService patientService) {
		PatientService = patientService;
	}

	//删除一个病人
	public void DeletePatient() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String patient_id = request.getParameter("patient_id");
		
		if(!cutil.IsNumber(patient_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int pid = Integer.valueOf(patient_id);
		
		patient = PatientService.QueryPatient(pid);
		
		if(patient == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		flag = PatientService.DeletePatient(patient);
		
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
	
	//查询病人总数
	public void CountPatient() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int count = PatientService.CountPatient();
		
		JSONObject jo = new JSONObject();
		
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//分页查询病人
	public void QueryPatientPageSize() throws Exception{
		
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
		
		List<Patient> PatientList = PatientService.QueryPatientByPageSize(PageSize, rows);
		int count = PatientService.CountPatient();
		
		JSONObject jo = new JSONObject();
		if(PatientList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", count);
			jo.put("rows", rows);
			jo.put("PageSize", PageSize);
			jo.put("Array", PatientList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
		
	}

	//更新病人信息
	public void UpdatePatient() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String patient_name = request.getParameter("patient_name");
		String patient_birthday = request.getParameter("patient_birthday");
		String patient_gender = request.getParameter("patient_gender");
		String patient_identity = request.getParameter("patient_identity");
		String patient_number = request.getParameter("patient_number");
		String patient_nation = request.getParameter("patient_nation");
		String patient_place = request.getParameter("patient_place");
		String patient_id = request.getParameter("patient_id");
		
		if(!cutil.IsNumber(patient_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int pid = Integer.valueOf(patient_id);
		
		patient = PatientService.QueryPatient(pid);
		
		if(patient == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		patient.setPatientName(patient_name);
		patient.setPatientBirthday(patient_birthday);
		patient.setPatientGender(patient_gender);
		patient.setPatientIndentity(patient_identity);
		patient.setPatientNumber(patient_number);
		patient.setPatientNation(patient_nation);
		patient.setPatientBirthday(patient_place);
		patient.setPatientPlace(patient_place);
		
		flag = PatientService.UpdatePatient(patient);
		
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
	
	//添加病人
	public void AddPatient() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String patient_name = request.getParameter("patient_name");
		String patient_birthday = request.getParameter("patient_birthday");
		String patient_gender = request.getParameter("patient_gender");
		String patient_identity = request.getParameter("patient_identity");
		String patient_number = request.getParameter("patient_number");
		String patient_nation = request.getParameter("patient_nation");
		String patient_place = request.getParameter("patient_place");
		String user_id = request.getParameter("user_id");
		
		if(!cutil.IsNumber(user_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int uid = Integer.valueOf(user_id);
		
		patient.setPatientName(patient_name);
		patient.setPatientBirthday(patient_birthday);
		patient.setPatientGender(patient_gender);
		patient.setPatientIndentity(patient_identity);
		patient.setPatientNumber(patient_number);
		patient.setPatientBirthday(patient_place);
		patient.setPatientPlace(patient_place);
		patient.setPatientNation(patient_nation);
		patient.setUserId(uid);
		
		flag = PatientService.AddPatient(patient);
		
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
}
