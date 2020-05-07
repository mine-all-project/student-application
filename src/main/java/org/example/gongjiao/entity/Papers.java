package org.example.gongjiao.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Papers extends BaseEntity {
    private String keyWords;
    private String title;
    @Column(columnDefinition = "longtext")
    private String content;
    @OneToOne
    private SysUser sysUser;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
