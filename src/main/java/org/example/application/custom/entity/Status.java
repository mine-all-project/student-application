package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.ApplicationException;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Status extends BaseEntity {
    @Column(columnDefinition = "tinyint not null comment '状态码'")
    private Code code;
    @Column(columnDefinition = "longtext comment '详情'")
    private String content;
    @ManyToMany
    private List<FileInfo> files;

    public Status next() {
        Status instance = new Status();
        instance.code = this.code.next(this.code);
        return instance;
    }
    public Status next(String content) {
        Status instance = new Status();
        instance.code = this.code.next(this.code);
        instance.content = content;
        return instance;
    }

    public Status close(String content) {
        Status instance = new Status(Code.ERROR);
        instance.content = content;
        return instance;
    }

    public Status() {
    }

    public Status(Code code) {
        this.code = code;
    }

    public Status(Code code, String content) {
        this.code = code;
        this.content = content;
    }

    @ToString
    public enum Code {
        ERROR(-1, "课题已关闭"),
        ZERO(0, "等待系主任审核"),
        ONE(1, "等待编写任务书"),
        TWO(2, "等待学生选题"),
        THREE(3, "一稿阶段"),
        FOUR(4, "中期检查阶段"),
        FIVE(5, "终稿阶段"),
        SIX(6, "等待填写指导教师表"),
        SEVEN(7, "等待填写评阅教师表"),
        EIGHT(8, "等待生成答辩现场用表"),
        NINE(9, "等待答辩成绩评定"),
        TEN(10, "优秀毕业论文评选阶段"),
        ELEVEN(11, "二次答辩阶段"),
        TWELVE(12, "课题已结束");
        @Getter
        private final int code;
        private final String text;

        Code(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public Code next(Code code) {
            for (Code value : Code.values()) {
                if (value.code == code.getCode() + 1) {
                    return value;
                }
            }
            throw new ApplicationException("流程异常");
        }

        public static Code getByCode(String code) {
            for (Code value : Code.values()) {
                if (value.code == Integer.parseInt(code)) {
                    return value;
                }
            }
            throw new ApplicationException("状态异常");
        }
    }
}
