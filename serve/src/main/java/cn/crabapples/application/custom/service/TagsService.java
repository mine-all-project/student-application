package cn.crabapples.application.custom.service;


import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;

import java.util.List;

/**
 * TODO 用户相关服务
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public interface TagsService {

    Tags saveTags(TagsForm form);

    List<Tags> getAll();

    void removeById(String id);
}
