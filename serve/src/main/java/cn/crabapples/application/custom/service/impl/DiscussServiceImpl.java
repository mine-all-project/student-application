package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.dao.DiscussDAO;
import cn.crabapples.application.custom.entity.Discuss;
import cn.crabapples.application.custom.form.DiscussForm;
import cn.crabapples.application.custom.service.DiscussService;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.dto.SysUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/9 1:59
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class DiscussServiceImpl implements DiscussService {
    private final DiscussDAO discussDAO;
    private final JwtConfigure jwtConfigure;
    private final UserDAO userDAO;
    @Value("${isDebug}")
    private boolean isDebug;


    public DiscussServiceImpl(DiscussDAO discussDAO, JwtConfigure jwtConfigure, UserDAO userDAO) {
        this.discussDAO = discussDAO;
        this.jwtConfigure = jwtConfigure;
        this.userDAO = userDAO;
    }

    @Override
    public void saveDiscuss(HttpServletRequest request, DiscussForm form) {
        discussDAO.save(form, getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    @Override
    public List<Discuss> getAll(String subjectId) {
        List<Discuss> result = discussDAO.getAll(subjectId);
        cleanUserData(result);
        return result;
    }

    void cleanUserData(List<Discuss> list) {
        list.forEach(e -> {
            SysUserDTO user = new SysUserDTO();
            BeanUtils.copyProperties(e.getSysUser(), user);
            e.setUser(user);
            cleanUserData(e.getChildren());
        });
    }

    @Override
    public void removeById(String id) {
        discussDAO.removeById(id);
    }
}
