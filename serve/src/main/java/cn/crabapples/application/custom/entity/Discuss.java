package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 评论实体类
 */
@Setter
@Getter
@Entity
public class Discuss extends BaseEntity {
    @ManyToOne
    private Subject subject;
    @ManyToOne
    @JSONField(serialize = false)
    private SysUser sysUser;
    @Transient
    private SysUserDTO user;
    @Column(columnDefinition = "longtext comment '内容'")
    private String content;
    @OneToMany
    private List<Discuss> children;

}
