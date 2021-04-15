package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * 访问请求实体类
 */
@Setter
@Getter
@Entity
public class RequestPull extends BaseEntity {
    @ManyToOne
    private Subject subject;

    @ManyToOne
    @JSONField(serialize = false)
    private SysUser sysUser;

    @Transient
    private SysUserDTO user;

    @Column(columnDefinition = "longtext comment '备注'")
    private String content;

    @Column(columnDefinition = "tinyint(1) default 1 comment '状态 0:生效 1:未生效 2:已拒绝'")
    private int status;

    public RequestPull(SysUser user, Subject subject) {
        this.sysUser = user;
        this.subject = subject;
    }

    public RequestPull() {

    }
}
