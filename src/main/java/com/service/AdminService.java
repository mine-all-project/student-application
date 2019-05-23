package com.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.entity.Device;
import com.entity.Job;
import com.entity.Question;
import com.entity.User;
import com.exception.ServiceException;
import com.mapper.AdminMapping;

@Service
public class AdminService {
	@Autowired
	AdminMapping adminMapping;
	@Autowired
	ManagerService managerService;
	@Autowired
	EmployeeService employeeService;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	public List<Job> getJob() {
		return adminMapping.getJob();
	}

	public Integer saveUser(User user) throws ServiceException {
		String id = user.getId();
		int status = 0;
		
		User user_1 = adminMapping.getUserByUsername(user.getUsername());
		if(null==user_1) {
			if(StringUtils.isEmpty(id)) {
				id = UUID.randomUUID().toString();
				user.setId(id);
				user.setNumber(sdf.format(new Date()));
				status = adminMapping.addUser(user);
			}else {
				status = adminMapping.updateUser(user);
			}
			return status;
		}else {
			throw new ServiceException("用户名已存在");
		}
	}

	public List<User> getUserNotManager() {
		List<User> users = adminMapping.getUserNotManager();
		for (User user : users) {
			Job job = adminMapping.getJobById(user.getJobNum());
			if(null!=job) {
				user.setJobName(job.getName());
			}
		}
		return users;
	}

	public Integer delUser(String id) {
		int status = adminMapping.delUser(id);
		if(status==1) {
			adminMapping.delWorkByUserId(id);
			adminMapping.delFreeByUserId(id);
			adminMapping.delUpdateJobByUserId(id);
		}
		return status;
	}

	public Integer upLevel(String id, int shiro) {
		if(shiro==2) {
			User user = employeeService.getUserInfoById(id);
			String jobNum = user.getJobNum();
			adminMapping.upLevelByJobNum(jobNum);
		}
		int status = adminMapping.upLevel(id,shiro);
		return status;
	}

	public List<Map<String, String>> updateJobList() {
		List<Map<String, String>> updateJobs = adminMapping.updateJobList();
		return updateJobs;
	}

	public Integer endUpdateJob(String id, int end) {
		int status = 0;
		if(end==1) {
			Map<String,String> updateJob = adminMapping.getUpdateJobByid(id);
			String new_id = updateJob.get("new_id");
			String user_id = updateJob.get("user_id");
			int i = adminMapping.updateUserJobNumByUserId(user_id,new_id);
			if(i==1) {
				status = adminMapping.delUpdateJobByid(id);
			}
		}else {
			status = adminMapping.delUpdateJobByid(id);
		}
		return status;
	}

	public int saveJob(Job job) {
		int status = 0;
		String id = job.getId();
		if(null==id) {
			id = sdf.format(new Date());
			job.setId(id);
			status = adminMapping.addJob(job);
		}
				
		return status;
	}

	public Integer delJob(String id) {
		int status = adminMapping.delJob(id);
		return status;
	}

	public Job getJobById(String id) {
		Job job = adminMapping.getJobById(id);
		return job;
	}

	public Integer mergeJob(String id, String ids) {
		int status = 0;
		if(null!=ids) {
			String [] idArray = ids.split(",");
			for (String string : idArray) {
				adminMapping.setUserJobNumByJobNum(id,string);
				adminMapping.setFreeJobNumByJobNum(id,string);
				adminMapping.setWorkJobNumByJobNum(id,string);
			}
			for (String string : idArray) {
				status = delJob(string);
			}
		}
		return status;
	}

	public List<Device> needDeviceList() {
		List<Device> devices = adminMapping.needDeviceList();
		return devices;
	}

	public Integer addDevice(String id,String number) {
		number = sdf.format(new Date());
		int status = adminMapping.addDevice(id,number);
		return status;
	}

	public Integer unAddDevice(String id) {
		int status = adminMapping.unAddDevice(id);
		return status;
	}

	public List<Map<String,Object>> feedbackDeviceList() {
		List<Map<String,Object>> questions = adminMapping.feedbackDeviceList();
		return questions;
	}

	public Integer enQuestion(String id) {
		int status = adminMapping.enQuestion(id);
		return status;
	}

	public Integer unQuestion(String id) {
		int status = adminMapping.unQuestion(id);
		return status;
	}

	public List<User> getUserByNameAndJobNum(String jobNum, String name) {
		
		List<User> users= adminMapping.getUserByNameAndJobNum(jobNum,name);
		for (User user : users) {
			Job job = adminMapping.getJobById(user.getJobNum());
			if(null!=job) {
				user.setJobName(job.getName());
			}
		}
		return users;
	}
	
	public List<Question> getQuestionList() {
		List<Question> questions = adminMapping.getQuestionList();
		return questions;
	}
	
}
