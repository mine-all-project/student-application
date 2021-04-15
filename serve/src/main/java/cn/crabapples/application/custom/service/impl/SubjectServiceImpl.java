package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.AssertUtils;
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
 * 科研项目功能实现类
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

    /**
     * 保存科研项目
     */
    @Override
    public Subject saveSubject(HttpServletRequest request, SubjectForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        form.setUserId(user.getId());
        form.setCreateBy(user);
        return subjectDAO.save(form);
    }

    /**
     * 获取项目列表
     */
    @Override
    public List<Subject> getAll() {
        return subjectDAO.getAll();
    }

    /**
     * 获取我发起的项目
     */
    @Override
    public List<Subject> getMineAll(HttpServletRequest request) {
        return subjectDAO.getMineAll(getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    /**
     * 获取我参与的项目
     */
    @Override
    public List<Subject> getMineJoin(HttpServletRequest request) {
        return subjectDAO.getMineJoin(getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    /**
     * 获取我发起的访问请求
     */
    @Override
    public List<SubjectShare> getMinePull(HttpServletRequest request) {
        return subjectDAO.getMinePull(getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    /**
     * 保存成果
     */
    @Override
    public void saveResultInfo(SubjectStepResultInfoForm form) {
        subjectDAO.saveResultInfo(form);
    }

    /**
     * 结束科研阶段
     */
    @Override
    public void endStepById(String id) {
        subjectDAO.endStepById(id);
    }

    /**
     * 结束科研项目
     */
    @Override
    public void endSubjectById(String id) {
        subjectDAO.endSubjectById(id);
    }

    /**
     * 分享科研项目
     */
    @Override
    public void shareById(String id, HttpServletRequest request) {
        SysUser sysUser = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        subjectDAO.shareById(id, sysUser);
    }

    /**
     * 取消分享
     */
    @Override
    public void closeShareById(String id) {
        subjectDAO.closeShareById(id);
    }

    /**
     * 获取科研项目待审核列表
     */
    @Override
    public List<Subject> getAuditList(HttpServletRequest request) {
        return subjectDAO.getAuditList();
    }

    /**
     * 审核通过
     */
    @Override
    public Subject accept(String id) {
        Subject entity = subjectDAO.findById(id);
        AssertUtils.notNull(entity, "项目不存在");
        entity.setStatus(1);
        return subjectDAO.save(entity);
    }

    /**
     * 审核拒绝
     */
    @Override
    public Subject unaccepted(String id) {
        Subject entity = subjectDAO.findById(id);
        AssertUtils.notNull(entity, "项目不存在");
        entity.setStatus(-1);
        return subjectDAO.save(entity);
    }
}
