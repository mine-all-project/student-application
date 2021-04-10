package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.common.utils.AssertUtils;
import cn.crabapples.application.custom.dao.jpa.TagsRepository;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.TagsForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/9 1:50
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Component
public class TagsDAO extends BaseDAO {
    private final TagsRepository tagsRepository;

    public TagsDAO(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }


    public Tags save(TagsForm form) {
        Tags entity = new Tags();
        BeanUtils.copyProperties(form, entity);
        return save(entity);
    }

    public Tags save(Tags entity) {
        return tagsRepository.saveAndFlush(entity);
    }

    public List<Tags> getAll() {
        return tagsRepository.findAllByDelFlagNot(desByCreateTime, IS_DEL);
    }

    public void removeById(String id) {
        Tags tags = tagsRepository.findByIdAndDelFlag(id, NOT_DEL);
        AssertUtils.notNull(tags, "删除失败");
        tags.setDelFlag(1);
        tagsRepository.saveAndFlush(tags);
    }

    public Tags findById(String id) {
        return tagsRepository.findByIdAndDelFlag(id, NOT_DEL);
    }

    public List<Tags> findByIds(List<String> ids) {
        return tagsRepository.findByIdInAndDelFlag(ids, NOT_DEL);
    }
}
