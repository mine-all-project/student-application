package org.example.yangjichang.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Animal extends BaseEntity {
    private BigDecimal sale;
    private Integer remaining;
    private String type;
    private String keyWord;
    @OneToMany
    private List<AudioFile> audioFiles;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
