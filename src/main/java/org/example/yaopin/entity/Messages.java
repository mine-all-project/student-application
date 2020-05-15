package org.example.yaopin.entity;//package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
public class Messages extends BaseEntity {
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public SysUser getFormAs() {
        return formAs;
    }

    public void setFormAs(SysUser formAs) {
        this.formAs = formAs;
    }

    public String getToAs() {
        return toAs;
    }

    public void setToAs(String toAs) {
        this.toAs = toAs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(columnDefinition = "tinyint(1) default 0 not null comment '消息类型 0:缺货 1:报损'")
    private Integer type;
    @Column(columnDefinition = "varchar(64) default 0 not null comment '数据Id'")
    private String objectId;
    @OneToOne
    private SysUser formAs;
    @Column(columnDefinition = "varchar(10) default 0 not null comment '接收方 0:管理员 1：采购员 2：库存员 3：销售员''")
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
