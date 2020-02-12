package CSU.Mental.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.Deadline;
import CSU.Mental.Service.DeadlineService;
import CSU.Mental.Utils.CommonUtils;
import CSU.Mental.Utils.DesUtil;

public class DeadlineAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private DesUtil dutil = new DesUtil();
	private DeadlineService DeadlineService;
	private Deadline deadline = new Deadline();
	
	public DeadlineService getDeadlineService() {
		return DeadlineService;
	}
	public void setDeadlineService(DeadlineService deadlineService) {
		DeadlineService = deadlineService;
	}
	
	public void AddOrUpdateDeadLine() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String activate_code = request.getParameter("activate_code");
		
		boolean flag = false;
		
		String datetime = dutil.strDec(activate_code, "MentalTest", "CSU", "vlab214");
		
		deadline = DeadlineService.QueryDeadline(1);
		
		if(deadline == null) {
			deadline.setDeadlineTime(datetime);
			flag = DeadlineService.AddDeadLine(deadline);
		}
		else {
			deadline.setDeadlineTime(datetime);
			flag = DeadlineService.UpdateDeadLine(deadline);
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
	
}
