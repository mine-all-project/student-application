package cn.crabapples.application.custom.service;


import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;

import java.util.List;

/**
 * 标签功能抽象接口
 */
public interface TagsService {

    Tags saveTags(TagsForm form);

    List<Tags> getAll();

    void removeById(String id);

    List<Tags> findByIds(List<String> ids);
}
