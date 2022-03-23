package org.example.application.custom.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.application.custom.entity.Message;

import java.util.List;
@Mapper
public interface MessageMapper {
    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByPaperId(@Param("paperId") String paperId);
}
