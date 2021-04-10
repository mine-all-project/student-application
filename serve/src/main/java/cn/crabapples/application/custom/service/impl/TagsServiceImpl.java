package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.custom.dao.TagsDAO;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;
import cn.crabapples.application.custom.service.TagsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/9 1:48
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class TagsServiceImpl implements TagsService {
    private final TagsDAO tagsDAO;

    public TagsServiceImpl(TagsDAO tagsDAO) {
        this.tagsDAO = tagsDAO;
    }

    @Override
    public Tags saveTags(TagsForm form) {
        return tagsDAO.save(form);
    }

    @Override
    public List<Tags> getAll() {
        return tagsDAO.getAll();
    }

    @Override
    public void removeById(String id) {
        tagsDAO.removeById(id);
    }

    @Override
    public List<Tags> findByIds(List<String> ids) {
        return tagsDAO.findByIds(ids);
    }
}