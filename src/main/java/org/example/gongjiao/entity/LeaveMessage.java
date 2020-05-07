package org.example.gongjiao.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class LeaveMessage extends BaseEntity{
    private Integer area;
    private Integer level;
    @Column(columnDefinition = "longtext")
    private String content;

    private String user;

    @OneToMany
    List<LeaveMessage> children;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
