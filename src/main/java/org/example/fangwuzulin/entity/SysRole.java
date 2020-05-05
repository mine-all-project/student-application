package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Getter
@Setter
public class SysRole extends BaseEntity {
    private String name;

    private List<SysMenu> sysMenus;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
