package org.example.shiyanshi.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
/*
 * 预约信息
 */
public class LineUps extends BaseEntity {
    @OneToOne
    private Machines machines;
    @OneToOne
    private SysUser user;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reallyStartTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reallyEndTime;

    @Column(columnDefinition = "varchar(128) comment '实验图片地址'")
    private String imgSrc;

    @Column(columnDefinition = "tinyint(2) default 0 not null comment '状态标记 0:未开始 1:已开始 2:已结束 3:已取消'")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
