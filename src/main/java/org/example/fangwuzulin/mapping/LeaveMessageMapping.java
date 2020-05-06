package org.example.fangwuzulin.mapping;

import org.example.fangwuzulin.entity.LeaveMessage;
import org.example.fangwuzulin.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveMessageMapping {
    Integer insertMessage(LeaveMessage message);
}
