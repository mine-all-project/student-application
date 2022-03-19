package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Setter
@Getter
@Entity
public class Demand extends BaseEntity {
    @Column(columnDefinition = "varchar(256) default null comment '名称'")
    private String title;
    @Column(columnDefinition = "varchar(256) default null comment '描述'")
    private String content;
    @Column(columnDefinition = "tinyint default null comment '商品类型 1:闲置 2:租赁'")
    private Integer type;
    @Column(columnDefinition = "tinyint default 1 comment '状态 0:正常 1:待审 2:驳回 3:'")
    private Integer status;
    @OneToOne
    private SysUser publisher;
    @OneToMany
    private List<FileInfo> images;
    @OneToMany
    private List<Message> messages;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
