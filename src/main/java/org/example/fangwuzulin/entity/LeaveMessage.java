package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



public class LeaveMessage extends BaseEntity {
    private String houses_id;
    private String user_id;
    private String content;
    private String parent_id;
    private SysUser user;
    private String houses;
    private String userName;
    private List<LeaveMessage> children;

    public String getHouses_id() {
        return houses_id;
    }

    public void setHouses_id(String houses_id) {
        this.houses_id = houses_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getHouses() {
        return houses;
    }

    public void setHouses(String houses) {
        this.houses = houses;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<LeaveMessage> getChildren() {
        return children;
    }

    public void setChildren(List<LeaveMessage> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public void setReturnMessage(LeaveMessage msg) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        if (msg != null) {
            this.children.add(msg);
        }
    }
}
