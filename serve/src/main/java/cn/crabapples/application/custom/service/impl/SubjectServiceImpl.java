package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.custom.dao.SubjectDAO;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.service.SubjectService;
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
