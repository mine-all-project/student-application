package cn.crabapples.system.dao;

import cn.crabapples.system.dao.jpa.TagsRepository;
import cn.crabapples.system.entity.Tags;
import cn.crabapples.system.form.TagsForm;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagsDAO {
    private final Sort sort = Sort.by("createTime").descending();
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
        return tagsRepository.findAll(sort);
    }
}
