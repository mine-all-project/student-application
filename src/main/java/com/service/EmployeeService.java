package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Question;
import com.entity.User;
import com.entity.Work;
import com.mapper.EmployeeMapping;

@Service
public class EmployeeService {
	@Autowired
	EmployeeMapping employeeMapping;
	public List<Work> getWorkListByUserId(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Work> works = employeeMapping.getWorkListByUserId(user.getId());
		return works;
	}
	
	public int subWork(Work work) {
		int status = employeeMapping.subWork(work);
		return status;
	}
	
	public User getUserInfoBySession(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user;
	}

	public User getUserInfoById(String id) {
		User user = employeeMapping.getUserInfoById(id);
		return user;
	}

	public int savePwd(String id, String password, String new_password, String new_password_1) {
		int status = 0;
		if(new_password_1.equals(new_password)) {
			status = employeeMapping.savePwd(id,password,new_password);
		}else {
			status = 0;
		}
		return status;
	}

	public int subUpdateJob(String user_id,String old_id, String new_id) {
		Map<String,String> updateJob = new HashMap<String,String>(4);
		updateJob.put("id", UUID.randomUUID().toString());
		updateJob.put("user_id", user_id);
		updateJob.put("old_id", old_id);
		updateJob.put("new_id", new_id);
		int status = employeeMapping.subUpdateJob(updateJob);
		return status;
	}

	public Integer saveFree(String user_id, String info, String time,String jobNum) {
		Map<String,String> free = new HashMap<String,String>(4);
		free.put("id", UUID.randomUUID().toString());
		free.put("user_id", user_id);
		free.put("info", info);
		free.put("time", time);
		free.put("jobNum", jobNum);
		int status = employeeMapping.saveFree(free);
		return status;
	}

	public Integer saveQuestion(Question question) {
		String id = question.getId();
		int status = 0;
		if(null==id) {
			id = UUID.randomUUID().toString();
			question.setId(id);
			status = employeeMapping.saveQuestion(question);
		}else {
			status = employeeMapping.updateQuestion(question);
		}
		return status;
	}
	
}
