package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Job;
import com.entity.User;
import com.entity.Work;
import com.service.AdminService;
import com.service.ManagerService;

@Controller
public class ManagerController {
	@Autowired
	ManagerService managerService;
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("addWork")
	public String addWork() {
		return "addWork";
	}
	
	@RequestMapping("workList")
	public String workList(HttpServletRequest request) {
		List<Work> works = managerService.getWorkListByJobNum(request);
		request.setAttribute("works", works);
		return "workList";
	}
	
	@RequestMapping("startWork")
	public String startWork(HttpServletRequest request,String id) {
		Work work = managerService.getWorkById(id);
		User user = (User) request.getSession().getAttribute("user");
		List<User> users = managerService.getUserByJobNumAndShiro(user.getJobNum(),1);
		request.setAttribute("work", work);
		request.setAttribute("users", users);
		return "startWork";
	}
	
	@RequestMapping("saveWork")
	public void saveWork(HttpServletRequest request,HttpServletResponse response,Work work) throws ServletException, IOException {
		Integer status = managerService.saveWork(work);
		System.out.println(status);
		request.getRequestDispatcher("workList").forward(request, response);
	}
	
	@RequestMapping("feedbackWork")
	@ResponseBody
	public String feedbackWork(HttpServletRequest request,String id) {
		Integer result = managerService.feedbackWork(id);
		return result.toString();
	}
	
	@RequestMapping("notJob")
	@ResponseBody
	public String notJob(HttpServletRequest request,String id,String why) {
		Integer result = managerService.notJob(id,why);
		return result.toString();
	}
	
	@RequestMapping("deleteWork")
	@ResponseBody
	public String deleteWork(HttpServletRequest request,String id) {
		Integer status = managerService.deleteWork(id);
		return status.toString();
	}
	
	@RequestMapping("moneyList")
	public String moneyList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Map<String, Object>> moneys = managerService.getMoneyListByJobId(user.getJobNum(),1);
		request.setAttribute("moneys", moneys);
		return "moneyList";
	}
	
	@RequestMapping("mangFree")
	public String mangFree(HttpServletRequest request) {
		String jobNum = ((User)request.getSession().getAttribute("user")).getJobNum();
		List<Map<String,Object>> frees= managerService.mangFree(jobNum);
		request.setAttribute("frees", frees);
		return "freeList";
	}
	
	@RequestMapping("updateFree")
	@ResponseBody
	public String updateFree(HttpServletRequest request,String id,int flag) {
		Integer status = managerService.updateFree(id,flag);
		return status.toString();
	}
	
	@RequestMapping("userList")
	public String userList(HttpServletRequest request) {
		String jobNum = ((User)request.getSession().getAttribute("user")).getJobNum();
		List<User> users= managerService.getUserByJobNumAndShiro(jobNum, 1);
		request.setAttribute("users", users);
		return "userList";
	}
	
	@RequestMapping("getMoneyTable")
	public ResponseEntity<byte[]> getMoneyTable(HttpServletRequest request) {
		ResponseEntity<byte[]> data = managerService.getMoneyTable(request);
		return data;
	}
	
	@RequestMapping("updateJob2")
	public String updateJob2(HttpServletRequest request) {
		String jobNum = ((User)request.getSession().getAttribute("user")).getJobNum();
		String jobName = adminService.getJobById(jobNum).getName();
		List<User> users= managerService.getUserByJobNumAndShiro(jobNum, 1);
		List<Job> jobs = adminService.getJob();
		request.setAttribute("jobs", jobs);
		request.setAttribute("users", users);
		request.setAttribute("jobName", jobName);
		return "updateJob2";
	}
}
