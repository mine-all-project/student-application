package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class SysMenu extends BaseEntity {
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public boolean isShowFlag() {
        return showFlag;
    }

    public void setShowFlag(boolean showFlag) {
        this.showFlag = showFlag;
    }

    @Column(columnDefinition = "int(4) default -1 comment '菜单排序号'")
    private Integer sort;

    @Column(columnDefinition = "varchar(64) comment '菜单图标'")
    private String icon;

    @Column(columnDefinition = "varchar(64) comment '菜单名'")
    private String name;

    @Column(columnDefinition = "varchar(64) comment '菜单链接'")
    private String url;

    @Column(columnDefinition = "int(2) default 0 comment '菜单类型 1:目录 2:菜单 3:外链 4:按钮'")
    private Integer menuType;

    @Column(columnDefinition = "varchar(64) default null comment '上级菜单id'")
    private String parentId;

    @Column(columnDefinition = "int(2) default 1 comment '菜单等级'")
    private Integer level;

    @Column(columnDefinition = "varchar(64) default null comment '授权标识'")
    private String permission;

    @Transient
    private List<SysMenu> children;

    @Transient
    private boolean showFlag;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
