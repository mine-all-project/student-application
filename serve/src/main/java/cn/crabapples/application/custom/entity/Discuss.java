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
 * TODO
 *
 * @author Mr.He
 * 2021/4/4 23:14
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
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
