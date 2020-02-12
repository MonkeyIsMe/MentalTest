package CSU.Mental.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.Mental.Model.User;
import CSU.Mental.Service.UserService;
import CSU.Mental.Utils.CommonUtils;
import net.sf.json.JSONObject;

public class UserAction extends ActionSupport{

	private CommonUtils cutil = new CommonUtils();
	private UserService UserService;
	private User user = new User();
	
	//增加一个用户
	public void AddUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String user_name = request.getParameter("user_name");
		String user_account = request.getParameter("user_account");
		String user_password = request.getParameter("user_password");
		String user_role = request.getParameter("user_role");
		
		boolean exist = UserService.QueryUserIsExist(user_account);
		if(exist) {
			out.println("Already");
			out.flush();
			out.close();
			return;
		}
		
		String password = cutil.md5(user_password);
		
		user.setUserAccount(user_account);
		user.setUserName(user_name);
		user.setUserPassword(password);
		user.setUserRole(user_role);
		
		flag = UserService.AddUser(user);
		
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
	
	//更新一个用户信息
	public void UpdateUserInfo() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String user_account = request.getParameter("user_account");
		String user_role = request.getParameter("user_role");
		
		if(!cutil.IsNumber(user_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int uid = Integer.valueOf(user_id);
		
		user = UserService.QueryUser(uid);
		
		if(user == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		user.setUserAccount(user_account);
		user.setUserName(user_name);
		user.setUserRole(user_role);
		
		flag = UserService.UpdateUser(user);
		
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
	
	//更新一个用户密码
	public void UpdateUserPassword() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		boolean flag = true;
		
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		
		String password = cutil.md5(user_password);
		
		if(!cutil.IsNumber(user_id)) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		int uid = Integer.valueOf(user_id);
		
		user = UserService.QueryUser(uid);
		
		if(user == null) {
			out.println("Fail");
			out.flush();
			out.close();
			return;
		}
		
		user.setUserPassword(password);
		
		flag = UserService.UpdateUser(user);
		
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
	
	//查询用户总数
	public void CountUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int count = UserService.CountUser();
		
		JSONObject jo = new JSONObject();
		
		jo.put("Count", count);
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//分页查询用户
	public void QueryUserPageSize() throws Exception{
		
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
		
		List<User> UserList = UserService.QueryUserPageSize(rows, PageSize);
		int count = UserService.CountUser();
		
		JSONObject jo = new JSONObject();
		if(UserList.size() == 0) {
			jo.put("Count", "0");
			jo.put("rows", "0");
			jo.put("PageSize", "0");
			jo.put("Array", "null");
		}
		else {
			jo.put("Count", count);
			jo.put("rows", rows);
			jo.put("PageSize", PageSize);
			jo.put("Array", UserList.toString());
		}
		
		out.println(jo.toString());
		out.flush();
		out.close();
		return;
	}
	
	//登录
	public void Login() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_account = request.getParameter("user_account");
		String user_password = request.getParameter("user_password");
		String user_code = request.getParameter("user_code");
		
		String password = cutil.md5(user_password);
		
		user = UserService.QueryUserByAccount(user_account);
		
		if(user == null) {
			out.println("NoUser");
			out.flush();
			out.close();
			return;
		}
		
		HttpSession session = request.getSession();
		String checkcode = (String) session.getAttribute("checkCode");
		String code = cutil.md5(user_code);
		
		boolean cflag = code.equals(checkcode);
		if(!cflag) {
			out.println("WrongCode");
			out.flush();
			out.close();
			return;
		}
		
		String upassword = user.getUserPassword();
		boolean pflag = password.equals(upassword);
		if(!pflag) {
			out.println("WrongPassword");
			out.flush();
			out.close();
			return;
		}
		
		out.println("Success");
		out.flush();
		out.close();
		return;
	}
}
