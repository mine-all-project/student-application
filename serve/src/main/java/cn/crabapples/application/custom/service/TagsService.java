package cn.crabapples.application.custom.service;


import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;

import java.util.List;

/**
 * TODO 
 *
 * @author Mr.He
 * 2021/4/9 1:49
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface TagsService {

    Tags saveTags(TagsForm form);

    List<Tags> getAll();

    void removeById(String id);
}
