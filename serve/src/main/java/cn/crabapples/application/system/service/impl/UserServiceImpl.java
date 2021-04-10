package cn.crabapples.application.system.service.impl;

import cn.crabapples.application.common.ApplicationException;
import cn.crabapples.application.common.config.ApplicationConfigure;
import cn.crabapples.application.common.utils.AssertUtils;
import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.service.TagsService;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import cn.crabapples.application.system.form.ResetPasswordForm;
import cn.crabapples.application.system.form.TagListForm;
import cn.crabapples.application.system.form.UserForm;
import cn.crabapples.application.system.service.UserService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 用户相关服务实现类
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
public class UserServiceImpl implements UserService {
    private final JwtConfigure jwtConfigure;
    private final TagsService tagsService;
    private final String salt;
    @Value("${isDebug}")
    private boolean isDebug;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    public UserServiceImpl(ApplicationConfigure applicationConfigure,
                           JwtConfigure jwtConfigure, TagsService tagsService, UserDAO userDAO) {
        this.jwtConfigure = jwtConfigure;
        this.tagsService = tagsService;
        this.userDAO = userDAO;
        this.salt = applicationConfigure.salt;
    }

    /**
     * 根据 [用户名] 查询用户
     *
     * @param username 用户名
     * @return 查询到的用户
     */
    @Override
    public SysUser findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public SysUser addUser(UserForm form) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(form, entity);
        entity.setTags(tagsService.findByIds(form.getTags()));
        return userDAO.save(entity);
    }

    @Override
    public SysUser editUser(UserForm form) {
        SysUser entity = userDAO.findById(form.getId());
        AssertUtils.notNull(entity, "用户不存在");
        BeanUtils.copyProperties(form, entity);
        entity.setTags(tagsService.findByIds(form.getTags()));
        return userDAO.save(entity);
    }

    @Override
    public void delUser(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setDelFlag(1);
        userDAO.save(user);
    }

    @Override
    public List<SysUser> findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public void changeStatus(String id) {
        SysUser user = userDAO.findById(id);
        AssertUtils.notNull(user, "用户不存在");
        user.setStatus(Math.abs(user.getStatus() - 1));
        userDAO.save(user);
    }

    @Override
    public List<SysUser> findAll() {
        logger.info("开始获取所有用户");
        return userDAO.findAll();
    }

    /**
     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @param status   状态
     * @param delFlag  删除标记
     * @return 查询到的用户
     */
    @Override
    public SysUser findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
        return userDAO.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag);
    }


    @Override
    public List<SysUserDTO> getUserListDTO(HttpServletRequest request) {
        String userId = getUserInfo(request, jwtConfigure, userDAO, isDebug).getId();
        List<SysUser> userList = userDAO.getUserList(userId, 0);
        List<SysUserDTO> userDTOS = new ArrayList<>(userList.size());
        userList.forEach(e -> {
            SysUserDTO sysUserDTO = new SysUserDTO();
            BeanUtils.copyProperties(e, sysUserDTO);
            userDTOS.add(sysUserDTO);
        });
        return userDTOS;
    }

    @Override
    public SysUser getUserInfo(HttpServletRequest request) {
        return getUserInfo(request, jwtConfigure, userDAO, isDebug);
    }

    @Override
    public void updateTags(HttpServletRequest request, TagListForm form) {
        SysUser user = getUserInfo(request);
        List<Tags> tagsList = tagsService.findByIds(form.getTagsList());
        user.setTags(tagsList);
        userDAO.save(user);
    }

    @Override
    public void resetPassword(ResetPasswordForm form) {
        String password = form.getPassword();
        String rePassword = form.getRePassword();
        if (!password.equals(rePassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        SysUser user = userDAO.findById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        password = Md5Crypt.md5Crypt(form.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        userDAO.save(user);
    }
}
