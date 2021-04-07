package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.entity.SysUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
public class RequestPull extends BaseEntity {
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private SysUser sysUser;

    @Column(columnDefinition = "longtext comment '备注'")
    private String content;

    @Column(columnDefinition = "tinyint(1) default 1 comment '状态 0:生效 1:未生效'")
    private int status;

    public RequestPull(SysUser user, Subject subject) {
        this.sysUser = user;
        this.subject = subject;
    }

    public RequestPull() {

    }
}
