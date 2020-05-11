package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LeaveMessage extends BaseEntity {
    private String houses_id;
    private String user_id;
    private String content;
    private String parent_id;
    private SysUser user;
    private String houses;
    private String userName;
    private List<LeaveMessage> children;


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
