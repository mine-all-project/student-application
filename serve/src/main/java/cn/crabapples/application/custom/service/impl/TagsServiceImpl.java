package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.custom.dao.TagsDAO;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;
import cn.crabapples.application.custom.service.TagsService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 系统相关服务实现类
 *
 * @author Mr.He
 * 2020/1/28 23:23
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class TagsServiceImpl implements TagsService {
    private TagsDAO tagsDAO;

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
}
