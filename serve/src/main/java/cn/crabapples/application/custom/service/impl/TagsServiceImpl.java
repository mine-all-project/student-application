package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.custom.dao.TagsDAO;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;
import cn.crabapples.application.custom.service.TagsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签功能实现类
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class TagsServiceImpl implements TagsService {
    private final TagsDAO tagsDAO;

    public TagsServiceImpl(TagsDAO tagsDAO) {
        this.tagsDAO = tagsDAO;
    }

    /**
     * 保存标签
     */
    @Override
    public Tags saveTags(TagsForm form) {
        return tagsDAO.save(form);
    }

    /**
     * 获取所有标签
     */
    @Override
    public List<Tags> getAll() {
        return tagsDAO.getAll();
    }

    /**
     * 删除标签
     */
    @Override
    public void removeById(String id) {
        tagsDAO.removeById(id);
    }

    /**
     * 根据id列表查资标签
     */
    @Override
    public List<Tags> findByIds(List<String> ids) {
        return tagsDAO.findByIds(ids);
    }
}
