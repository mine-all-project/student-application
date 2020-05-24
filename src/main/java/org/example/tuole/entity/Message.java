package org.example.tuole.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author hequan@gogpay.cn
 * @date 2020/4/9 16:44
 */
@Entity
@Getter
@Setter
/**
 * 留言表
 */
public class Message extends BaseEntity{
    private Integer area;
    private Integer level;
    @Column(columnDefinition = "longtext")
    private String content;

    private String user;

    @OneToMany
    List<Message> children;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
