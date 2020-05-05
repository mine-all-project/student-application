package org.example.fangwuzulin.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    /**
     * Id uuid主键
     * GeneratedValue 自增长
     */
    @Id
    private String id;

    /**
     * 创建时间
     * columnDefinition 设置默认值为当前时间
     */
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss E")
    private LocalDateTime createTime;

    /**
     * 更新时间
     * columnDefinition 设置默认值为当前时间，随每次更新数据时更新时间
     */
    @LastModifiedDate
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
