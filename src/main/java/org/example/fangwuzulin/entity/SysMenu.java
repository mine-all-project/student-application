package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysMenu extends BaseEntity {
    private Integer sort;

    private String icon;

    private String name;

    private String url;

    private Integer menuType;

    private String parentId;

    private Integer level;

    private String permission;

    private List<SysMenu> children;

    private boolean showFlag;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
