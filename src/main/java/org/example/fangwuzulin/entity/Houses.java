package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Houses extends BaseEntity {
    private String title;
    private String address;
    private String user_id;
    private BigDecimal price;
    private String note;
    private String img_src;
    private String contract_id;
    private Contracts contracts;
    private String province;
    private String city;
    private String area;
    private SysUser user;
    private List<LeaveMessage> messages;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
