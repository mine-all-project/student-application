package org.example.shiyanshi.entity;//package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.base.BaseEntity;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Messages extends BaseEntity {
    @Column(columnDefinition = "tinyint(1) default 0 not null comment '消息类型 0:缺货 1:报损'")
    private Integer type;
    @Column(columnDefinition = "varchar(64) default 0 not null comment '数据Id'")
    private String objectId;
    @OneToOne
    private SysUser formAs;
    @Column(columnDefinition = "varchar(10) default 0 not null comment '接收方 0:管理员 1：采购员 2：库存员 3：销售员'")
    private String toAs;
    @Column(columnDefinition = "tinyint(1) default 0 not null comment '阅读状态 0:未读 1:已读'")
    private Integer status;
    @Transient
    private String content;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
