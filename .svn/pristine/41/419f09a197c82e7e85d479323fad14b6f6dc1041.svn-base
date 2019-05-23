package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.User;
import com.entity.Work;

@Repository
public interface ManagerMapping {

	List<Work> getWorkListByJobNum(@Param("jobNum")String jobNum);

	Work getWorkById(@Param("id")String id);

	List<User> getUserByJobNumAndShiro(@Param("jobNum")String jobNum,@Param("shiro") int shiro);

	int insertWork(Work work);

	int startWork(Work work);

	List<Work> getWorkListByUserId(String id);

	int feedbackWork(@Param("id")String id);

	int deleteWork(@Param("id")String id);

	List<Map<String,Object>> getFreeByJobNum(String jobNum);

	int updateFree(@Param("id")String id, @Param("status")int flag);

	List<Map<String, Object>> getFreeByUserId(@Param("user_id")String user_id);

	int notJob(@Param("id")String id, @Param("why")String why);
}