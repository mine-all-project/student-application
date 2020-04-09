package cn.crabapples.tuole.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author hequan@gogpay.cn
 * @date 2020/4/9 15:40
 */
@Entity
@Getter
@Setter
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
