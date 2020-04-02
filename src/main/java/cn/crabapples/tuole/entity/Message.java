package cn.crabapples.tuole.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Message extends BaseEntity{
    @Column(length = 32)
    private String userId;
    @Column(length = 50)
    private String name;
    @Column(columnDefinition = "int(2) default 1 comment '模板'")
    private Integer template;
    @Column(length = 1)
    private String status;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
