package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.ApplicationException;
import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.common.utils.AssertUtils;
import cn.crabapples.application.custom.dao.jpa.DiscussRepository;
import cn.crabapples.application.custom.dao.jpa.SubjectRepository;
import cn.crabapples.application.custom.entity.Discuss;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.DiscussForm;
import cn.crabapples.application.system.entity.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/9 1:59
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Component
public class DiscussDAO extends BaseDAO {
    private final SubjectRepository subjectRepository;
    private final DiscussRepository discussRepository;

    public DiscussDAO(SubjectRepository subjectRepository,
                      DiscussRepository discussRepository) {
        this.subjectRepository = subjectRepository;
        this.discussRepository = discussRepository;
    }


    public Discuss save(DiscussForm form, SysUser user) {
        String parentId = form.getParentId();
        String subjectId = form.getSubjectId();
        Discuss entity = new Discuss();
        if (!StringUtils.isEmpty(parentId)) {
            Discuss parent = discussRepository.findByIdAndDelFlag(parentId, NOT_DEL);
            AssertUtils.notNull(parent, "评论失败");
            BeanUtils.copyProperties(form, entity);
            entity.setSysUser(user);
            List<Discuss> children = parent.getChildren();
            children.add(entity);
            discussRepository.saveAndFlush(entity);
            return discussRepository.saveAndFlush(parent);
        } else if (!StringUtils.isEmpty(subjectId)) {
            Subject subject = subjectRepository.findByIdAndDelFlag(subjectId, NOT_DEL);
            AssertUtils.notNull(subject, "项目已被删除");
            BeanUtils.copyProperties(form, entity);
            entity.setSubject(subject);
            entity.setSysUser(user);
            return discussRepository.saveAndFlush(entity);
        }
        throw new ApplicationException("评论失败");
    }

    public List<Discuss> getAll(String subjectId) {
        Subject subject = subjectRepository.findByIdAndDelFlag(subjectId, NOT_DEL);
        List<Discuss> list = discussRepository.findBySubjectAndDelFlag(ascByCreateTime, subject, NOT_DEL);
        return cleanDeleteData(list);
    }

    private List<Discuss> cleanDeleteData(List<Discuss> list) {
        list = list.stream().filter(e -> {
            e.setChildren(cleanDeleteData(e.getChildren()));
            return e.getDelFlag() == 0;
        }).collect(Collectors.toList());
        return list;
    }


    public void removeById(String id) {
        Discuss discuss = discussRepository.findByIdAndDelFlag(id, NOT_DEL);
        discuss.setDelFlag(1);
        discussRepository.saveAndFlush(discuss);
    }
}
