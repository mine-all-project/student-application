package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(schema = "system")
public class Goods extends BaseEntity {
    //名称
    @Column(columnDefinition = "varchar(256) default null ")
    private String name;
    //价格
    @Column(columnDefinition = "int default 0 ")
    private Integer price;
    //描述
    @Column(columnDefinition = "varchar(256) default null ")
    private String content;
    //商品类型 1:闲置 2:租赁
    private Integer type;
    //状态 0:正常 1:待审 2:驳回
    private Integer status;
    //审核备注
    @Column(columnDefinition = "varchar(256) default null ")
    private String note;
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
