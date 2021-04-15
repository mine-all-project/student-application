package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * 科研项目阶段实体类
 */

@Setter
@Getter
@Entity
public class SubjectStep extends BaseEntity {
    @Column(columnDefinition = "tinyint(1) default 0 comment '阶段数'")
    private int indexNum;

    @Column(columnDefinition = "longtext comment '阶段内容'")
    private String content;

    @Column(columnDefinition = "tinyint(1) default 0 comment '状态 0:正常 1:已结束'")
    private int status;

    @OneToMany
    private List<SubjectStepResultInfo> resultInfos;

}