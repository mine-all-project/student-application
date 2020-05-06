package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    private String id;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime updateTime;

    /**
     * 删除标记 (0:正常 1:删除)
     */
    private int delFlag;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
