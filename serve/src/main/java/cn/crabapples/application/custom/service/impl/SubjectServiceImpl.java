package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.dao.SubjectDAO;
import cn.crabapples.application.custom.dao.SubjectShareDAO;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.entity.SubjectShare;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.form.SubjectStepResultInfoForm;
import cn.crabapples.application.custom.service.SubjectService;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    private final JwtConfigure jwtConfigure;
    private final SubjectDAO subjectDAO;
    private final UserDAO userDAO;
    private final SubjectShareDAO subjectShareDAO;
    @Value("${isDebug}")
    private boolean isDebug;

    public SubjectServiceImpl(JwtConfigure jwtConfigure, SubjectDAO subjectDAO,
                              UserDAO userDAO, SubjectShareDAO subjectShareDAO) {
        this.jwtConfigure = jwtConfigure;
        this.subjectDAO = subjectDAO;
        this.userDAO = userDAO;
        this.subjectShareDAO = subjectShareDAO;
    }

    @Override
    public Subject saveSubject(HttpServletRequest request, SubjectForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        form.setUserId(user.getId());
        form.setCreateBy(user);
        return subjectDAO.save(form);
    }


    @Override
    public List<Subject> getAll() {
        return subjectDAO.getAll();
    }

    @Override
    public List<Subject> getMineAll(HttpServletRequest request) {
        return subjectDAO.getMineAll(getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    @Override
    public List<Subject> getMineJoin(HttpServletRequest request) {
        return subjectDAO.getMineJoin(getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    @Override
    public List<SubjectShare> getMinePull(HttpServletRequest request) {
        return subjectDAO.getMinePull(getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    @Override
    public void saveResultInfo(SubjectStepResultInfoForm form) {
        subjectDAO.saveResultInfo(form);
    }

    @Override
    public void endStepById(String id) {
        subjectDAO.endStepById(id);
    }

    @Override
    public void endSubjectById(String id) {
        subjectDAO.endSubjectById(id);
    }

    @Override
    public void shareById(String id, HttpServletRequest request) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        subjectDAO.shareById(id, sysUser);
    }

    @Override
    public void closeShareById(String id) {
        subjectDAO.closeShareById(id);
    }
}
