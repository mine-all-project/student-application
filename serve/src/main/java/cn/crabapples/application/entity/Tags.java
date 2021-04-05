package cn.crabapples.application.entity;

import cn.crabapples.common.BaseEntity;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
//@Document("FileData")
public class Tags extends BaseEntity {
    private String name;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
