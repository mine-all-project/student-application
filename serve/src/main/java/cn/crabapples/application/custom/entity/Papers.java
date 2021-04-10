package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 14:43
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
//@Entity
@Getter
@Setter
//@Document("FileData")
public class Papers extends BaseEntity {
    private String title;
    private String content;
    private SysUser createBy;
    private SysUserDTO user;
    private List<Tags> tagsList;
    private List<String> fileList;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
