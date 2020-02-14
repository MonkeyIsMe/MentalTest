package CSU.Mental.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Hospital;
import CSU.Mental.Service.HospitalService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONObject;

public class HospitalAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private HospitalService HospitalService;
	private Hospital hospital = new Hospital();
	
	public HospitalService getHospitalService() {
		return HospitalService;
	}
	public void setHospitalService(HospitalService hospitalService) {
		HospitalService = hospitalService;
	}
	
	//添加医院信息
	public void AddOrUpdateHospital() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = false;
		
		String hospital_name = request.getParameter("hospital_name");
		String hospital_img = request.getParameter("hospital_img");
		String hospital_department = request.getParameter("hospital_department");
		String hospital_info = request.getParameter("hospital_info");
		
		hospital = HospitalService.QueryHospital(1);
		
		if(hospital == null) {
			hospital.setHospitalName(hospital_name);
			hospital.setHospitalImage(hospital_img);
			hospital.setHospitalDepartment(hospital_department);
			hospital.setHospitalInfo(hospital_info);
			
			flag = HospitalService.AddHospital(hospital);
		}
		else {
			hospital.setHospitalName(hospital_name);
			hospital.setHospitalImage(hospital_img);
			hospital.setHospitalDepartment(hospital_department);
			hospital.setHospitalInfo(hospital_info);
			
			flag = HospitalService.UpdateHospital(hospital);
		}

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
