package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveMessage extends BaseEntity {
    private String houses_id;
    private String user_id;
    private String content;
    private String parent_id;
    private SysUser user;
    private LeaveMessage returnMessage;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
