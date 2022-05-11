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
    @Column(columnDefinition = "longtext comment '备注'")
    private String next;
    @Column(columnDefinition = "longtext comment '内容'")
    private String content;
    @ManyToMany
    private List<FileInfo> files;


    public Status next(String next,String content) {
        Status instance = new Status();
        instance.code = this.code.next(this.code);
        instance.next = next;
        instance.content = content;
        return instance;
    }

    public Status close(String next) {
        Status instance = new Status(Code.ERROR);
        instance.next = next;
        return instance;
    }

    public Status() {
    }

    public Status(Code code) {
        this.code = code;
    }

    public Status(Code code, String next) {
        this.code = code;
        this.next = next;
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
        SIX(6, "等待填写指导教师表");
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
