package cn.crabapples.tuole.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO
 *
 * @author Mr.He
 * 2020/4/2 20:48
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Entity
@Getter
@Setter
public class Picture extends BaseEntity {
    @Column(columnDefinition = "longtext")
    private String url;
    @Column(columnDefinition = "varchar(64)")
    private String keyFlag;
    private Integer sort;
}
