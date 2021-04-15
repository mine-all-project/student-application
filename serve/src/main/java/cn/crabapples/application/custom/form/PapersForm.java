package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 文章表单（elasticsearch，暂未实现）
 */
@Getter
@Setter
public class PapersForm extends BaseForm {
    private String title;
    private String content;
    private List<String> tagsList;
    private List<Map<String, String>> fileList;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
