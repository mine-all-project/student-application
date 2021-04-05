package cn.crabapples.application.entity;

import cn.crabapples.common.BaseEntity;
import cn.crabapples.system.entity.SysUser;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

    @Column(columnDefinition = "tinyint(5) default 0 comment '状态'")
    private int status;

    @OneToMany
    private List<Tags> tags;

    @Column(columnDefinition = "timestamp default current_timestamp comment '开始时间'")
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime startTime;

    @Column(columnDefinition = "timestamp comment '结束时间'")
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime endTime;

    @OneToMany
    private List<SysUser> personList;

    @OneToMany
    private List<Step> stepList;

    @CreatedBy
    private String userId;

    @Setter
    @Getter
    @Entity
    public static class Step extends BaseEntity {
        @Column(columnDefinition = "int(3) comment '阶段数'")
        private int indexNum;

        @Column(columnDefinition = "longtext comment '阶段内容'")
        private String content;

        @Column(columnDefinition = "timestamp comment '完成时间'")
        @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
        private LocalDateTime endTime;
    }
}
