package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Question;
import com.entity.User;
import com.entity.Work;

@Repository
public interface EmployeeMapping {
	List<Work> getWorkListByUserId(String id);

	int subWork(Work work);

	User getUserInfoById(String id);

	int savePwd(@Param("id")String id, @Param("password")String password, @Param("new_password")String new_password);

	int subUpdateJob(@Param("map")Map<String, String> map);

	int saveFree(@Param("map")Map<String, String> free);
	
	int saveQuestion(Question question);

	int updateQuestion(Question question);

}