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
 * 评论功能实现类
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

    /**
     * 保存评论
     */
    @Override
    public void saveDiscuss(HttpServletRequest request, DiscussForm form) {
        discussDAO.save(form, getUserInfo(request, jwtConfigure, userDAO, isDebug));
    }

    /**
     * 获取评论列表
     */
    @Override
    public List<Discuss> getAll(String subjectId) {
        List<Discuss> result = discussDAO.getAll(subjectId);
        cleanUserData(result);
        return result;
    }

    /**
     * 过滤敏感用户数据
     */
    void cleanUserData(List<Discuss> list) {
        list.forEach(e -> {
            SysUserDTO user = new SysUserDTO();
            BeanUtils.copyProperties(e.getSysUser(), user);
            e.setUser(user);
            cleanUserData(e.getChildren());
        });
    }

    /**
     * 删除评论
     */
    @Override
    public void removeById(String id) {
        discussDAO.removeById(id);
    }
}
