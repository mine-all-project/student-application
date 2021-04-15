package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 文章实体类(elasticsearch,暂未实现)
 */
//@Entity
@Getter
@Setter
//@Document("FileData")
public class Papers extends BaseEntity {
    private String title;
    private String content;
    private SysUser createBy;
    private Map<String, Object> user;
    private List<Map<String, Object>> tagsList;
    private List<Map<String, String>> fileList;
    private int readerCount;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
