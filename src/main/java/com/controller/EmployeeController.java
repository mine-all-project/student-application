package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Job;
import com.entity.Question;
import com.entity.User;
import com.entity.Work;
import com.service.EmployeeService;
import com.service.AdminService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	AdminService adminService;
	
	@RequestMapping("submit_work")
	public String submit_work(HttpServletRequest request) {
		List<Work> works = employeeService.getWorkListByUserId(request);
		request.setAttribute("works", works);
		return "submitWork";
	}
	
	@RequestMapping("subWork")
	public String subWork(HttpServletRequest request,Work work) {
		Integer status = employeeService.subWork(work);
		request.setAttribute("result", status);
		return "result";
	}
	
	@RequestMapping("updateJob")
	public String updateJob(HttpServletRequest request) {
		List<Job> jobs = adminService.getJob();
		request.setAttribute("jobs", jobs);
		return "updateJob";
	}
	
	@RequestMapping("subUpdateJob")
	public String subUpdateJob(HttpServletRequest request,String user_id,String old_id,String id) {
		Integer status = employeeService.subUpdateJob(user_id,old_id,id);
		request.setAttribute("result", status);
		return "result";
	}
	
	@RequestMapping("userInfo")
	public String userInfo(HttpServletRequest request) {
		String id = ((User)request.getSession().getAttribute("user")).getId();
		User user = employeeService.getUserInfoById(id);
		request.setAttribute("user", user);
		return "userInfo";
	}
	
	@RequestMapping("updatePassword")
	public String updatePassword(HttpServletRequest request) {
		User user = employeeService.getUserInfoBySession(request);
		request.setAttribute("user", user);
		return "updatePassword";
	}
	
	@RequestMapping("savePwd")
	public String savePwd(HttpServletRequest request,String id,String password,String new_password,String new_password_1) {
		Integer status = employeeService.savePwd(id,password,new_password,new_password_1);
		request.setAttribute("result", status);
		return "result";
	}
	
	@RequestMapping("addFree")
	public String addFree() {
		return "addFree";
	}
	
	@RequestMapping("saveFree")
	public String saveFree(HttpServletRequest request,String user_id,String info, String time,String jobNum) {
		Integer status = employeeService.saveFree(user_id,info,time,jobNum);
		request.setAttribute("result", status);
		return "result";
	}
	
	@RequestMapping("saveQuestion")
	public String saveQuestion(HttpServletRequest request,Question question) throws ServletException, IOException {
		Integer status = employeeService.saveQuestion(question);
		List<Question> questions = adminService.getQuestionList();
		request.setAttribute("questions", questions);
		return "questionList";
		
	}
	
	@RequestMapping("saveUserInfo")
	public void saveUser(HttpServletRequest request,HttpServletResponse response,User user) throws ServletException, IOException {
		try {
			Integer status = adminService.saveUser(user);
		}catch(Exception e){
		}
		request.getRequestDispatcher("userInfo").forward(request, response);
	}
}
