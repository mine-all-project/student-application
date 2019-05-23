package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Device;
import com.entity.Job;
import com.entity.User;
import com.google.gson.Gson;
import com.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@RequestMapping("addUser")
	public String addUser(HttpServletRequest request) {
		List<Job> jobs = adminService.getJob();
		request.setAttribute("jobs", jobs);
		return "addUser";
	}
	
	@RequestMapping("saveUser")
	public void saveUser(HttpServletRequest request,HttpServletResponse response,User user) throws ServletException, IOException {
		try {
			Integer status = adminService.saveUser(user);
			request.setAttribute("result", status);
			request.setAttribute("message", "");
		}catch(Exception e){
			request.setAttribute("result", 0);
			request.setAttribute("message", e.getMessage());
		}
		request.getRequestDispatcher("delUserList").forward(request, response);
	}
	
	@RequestMapping("delUserList")
	public String delUserList(HttpServletRequest request) {
		List<User> users = adminService.getUserNotManager();
		request.setAttribute("users", users);
		return "delUserList";
	}
	
	@RequestMapping("delUser")
	@ResponseBody
	public String delUser(String id) {
		Integer status = adminService.delUser(id);
		return status.toString();
	}
	
	@RequestMapping("updateLevel")
	public String updateLevel(HttpServletRequest request) {
		List<User> users = adminService.getUserNotManager();
		request.setAttribute("users", users);
		return "updateLevel";
	}
	
	@RequestMapping("upLevel")
	@ResponseBody
	public String upLevel(String id,int shiro) {
		Integer status = adminService.upLevel(id,shiro);
		return status.toString();
	}
	
	@RequestMapping("updateJobList")
	public String updateJobList(HttpServletRequest request) {
		List<Map<String,String>> updateJobs = adminService.updateJobList();
		request.setAttribute("updateJobs", updateJobs);
		return "updateJobList";
	}
	
	@RequestMapping("endUpdateJob")
	public String endUpdateJob(HttpServletRequest request,String id,int end) {
		Integer status = adminService.endUpdateJob(id,end);
		request.setAttribute("result", status);
		return "result";
	}
	
	@RequestMapping("addJob")
	public String addJob() {
		return "addJob";
	}
	
	@RequestMapping("saveJob")
	public void saveJob(HttpServletRequest request,HttpServletResponse response,Job job) throws ServletException, IOException {
		Integer status = adminService.saveJob(job);
		request.setAttribute("result", status);
		request.getRequestDispatcher("delJobList").forward(request, response);
	}
	
	@RequestMapping("delJobList")
	public String delJobList(HttpServletRequest request) {
		List<Job> jobs = adminService.getJob();
		request.setAttribute("jobs", jobs);
		return "delJobList";
	}
	
	@RequestMapping("delJob")
	@ResponseBody
	public String delJob(String id) {
		Integer status = adminService.delJob(id);
		return status.toString();
	}
	
	@RequestMapping("mergeJobList")
	public String mergeJobList(HttpServletRequest request,String id) {
		Job job = adminService.getJobById(id);
		List<Job> jobs = adminService.getJob();
		request.setAttribute("job", job);
		request.setAttribute("jobs", jobs);
		return "mergeJob";
	}
	
	@RequestMapping("mergeJob")
	public String mergeJob(HttpServletRequest request,String id,String ids) {
		Integer status = adminService.mergeJob(id,ids);
		System.out.println(status);
		List<Job> jobs = adminService.getJob();
		request.setAttribute("jobs", jobs);
		return "delJobList";
	}
	
	@RequestMapping("needDeviceList")
	public String needDeviceList(HttpServletRequest request) {
		List<Device> devices = adminService.needDeviceList();
		request.setAttribute("devices", devices);
		return "needDeviceList";
	}
	
	@RequestMapping("addDevice")
	@ResponseBody
	public String addDevice(String id,String number) {
		Integer status = adminService.addDevice(id,number);
		return status.toString();
	}
	
	@RequestMapping("unAddDevice")
	@ResponseBody
	public String unAddDevidce(String id) {
		Integer status = adminService.unAddDevice(id);
		return status.toString();
	}
	
	@RequestMapping("feedbackDeviceList")
	public String feedbackDeviceList(HttpServletRequest request) {
		List<Map<String,Object>> questions = adminService.feedbackDeviceList();
		request.setAttribute("questions", questions);
		return "feedbackDeviceList";
	}
	
	@RequestMapping("enQuestion")
	@ResponseBody
	public String enQuestion(String id) {
		Integer status = adminService.enQuestion(id);
		return status.toString();
	}
	
	@RequestMapping("unQuestion")
	@ResponseBody
	public String unQuestion(String id) {
		Integer status = adminService.unQuestion(id);
		return status.toString();
	}
	
	@RequestMapping("getUserByName")
	@ResponseBody
	public String getUserByNameAndJobNum(HttpSession session,String name) {
		User user = (User) session.getAttribute("user");
		List<User> users = adminService.getUserByNameAndJobNum(user.getJobNum(),name);
		return new Gson().toJson(users);
	}
}
