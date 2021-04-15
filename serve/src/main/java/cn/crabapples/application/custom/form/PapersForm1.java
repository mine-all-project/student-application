package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import cn.crabapples.application.custom.entity.PaperFileInfo;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 文章表单（mysql）
 */
@Getter
@Setter
public class PapersForm1 extends BaseForm {
    private String title;
    private String content;
    private List<String> tagsList;
    private List<PaperFileInfo> fileList;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
