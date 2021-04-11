package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
public class Subject extends BaseEntity {
    @Column(columnDefinition = "varchar(64) comment '标题'")
    private String title;

    @Column(columnDefinition = "tinyint(1) default 0 comment '状态 0:立项 1:在研 2:结题 -1:驳回'")
    private Integer status;

    @Column(columnDefinition = "tinyint(1) default 1 comment '是否共享 0:是 1:否'")
    private Integer isShare;

    @Column(columnDefinition = "timestamp default current_timestamp comment '开始时间'")
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss ")
    private LocalDateTime beginTime;

    @Column(columnDefinition = "timestamp comment '结束时间'")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss ")
    private LocalDateTime endTime;

    @ManyToMany
    private List<SysUser> personList;

    @OneToMany
    private List<SubjectStep> stepList;

    @OneToMany
    private List<Tags> tags;

    @ManyToOne
//    @JoinColumn(name = "")
    private SysUser createBy;


}
