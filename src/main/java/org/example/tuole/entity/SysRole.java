package org.example.tuole.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class SysRole extends BaseEntity {
    @Column(columnDefinition = "varchar(64) ")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<SysMenu> sysMenus;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
