package cn.crabapples.system.service.impl;

import cn.crabapples.system.dao.SubjectDAO;
import cn.crabapples.system.entity.Subject;
import cn.crabapples.system.form.SubjectForm;
import cn.crabapples.system.service.SubjectService;
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
public class SubjectServiceImpl implements SubjectService {
    private SubjectDAO subjectDAO;

    public SubjectServiceImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    public Subject saveSubject(SubjectForm form) {
        return subjectDAO.save(form);
    }

    @Override
    public List<Subject> getAll() {
        return subjectDAO.getAll();
    }
}
