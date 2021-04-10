package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
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
@Getter
@Setter
public class PapersForm extends BaseForm {
    private String title;
    private String content;
    private List<String> tagsList;
    private List<String> fileList;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
