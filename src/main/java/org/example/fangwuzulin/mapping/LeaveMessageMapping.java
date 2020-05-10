package org.example.fangwuzulin.mapping;

import org.example.fangwuzulin.entity.LeaveMessage;
import org.example.fangwuzulin.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveMessageMapping {
    Integer insertMessage(LeaveMessage message);

    List<LeaveMessage> findAllByHousesId(String houses_id);

    List<LeaveMessage> findById(String id);

    LeaveMessage findByParentId(String id);

    List<LeaveMessage> findAll();
}
