package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.common.utils.jwt.JwtTokenUtils;
import cn.crabapples.application.custom.dao.SubjectDAO;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.custom.form.Subject$Step$ResultInfoForm;
import cn.crabapples.application.custom.form.SubjectForm;
import cn.crabapples.application.custom.service.SubjectService;
import cn.crabapples.application.system.dao.UserDAO;
import io.jsonwebtoken.Claims;
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
    @Value("${isDebug}")
    private boolean isDebug;

    public SubjectServiceImpl(JwtConfigure jwtConfigure, SubjectDAO subjectDAO, UserDAO userDAO) {
        this.jwtConfigure = jwtConfigure;
        this.subjectDAO = subjectDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Subject saveSubject(HttpServletRequest request, SubjectForm form) {
        String userId = "admin";
        if (!isDebug) {
            final String authHeader = request.getHeader(jwtConfigure.getAuthKey());
            Claims claims = JwtTokenUtils.parseJWT(authHeader, jwtConfigure.getBase64Secret());
            userId = String.valueOf(claims.get("userId"));
        }
        form.setUserId(userId);
        form.setCreateBy(userDAO.findByUsername(userId));
        return subjectDAO.save(form);
    }

    @Override
    public List<Subject> getAll() {
        return subjectDAO.getAll();
    }

    @Override
    public List<Subject.Step> getStepList(String subjectId) {
        return subjectDAO.getStepList(subjectId);
    }

    @Override
    public void saveResultInfo(Subject$Step$ResultInfoForm form) {
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
}
