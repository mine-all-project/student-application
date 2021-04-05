package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
//@Document("FileData")
public class Tags extends BaseEntity {
    @Column(columnDefinition = "varchar(64) comment '标签名称'")
    private String name;
    @Column(columnDefinition = "varchar(64) comment '标签样式'")
    private String color;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
