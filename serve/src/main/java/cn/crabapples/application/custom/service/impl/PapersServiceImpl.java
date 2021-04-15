package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.dao.PapersDAO;
import cn.crabapples.application.custom.entity.Papers;
import cn.crabapples.application.custom.form.PapersForm;
import cn.crabapples.application.custom.service.PapersService;
import cn.crabapples.application.custom.service.TagsService;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文章功能实现类（elasticsearch，暂未实现）
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class PapersServiceImpl implements PapersService {
    private final PapersDAO papersDAO;
    private final TagsService tagsService;
    private final JwtConfigure jwtConfigure;
    private final UserDAO userDAO;
    @Value("${isDebug}")
    private boolean isDebug;

    public PapersServiceImpl(PapersDAO papersDAO, TagsService tagsService, JwtConfigure jwtConfigure, UserDAO userDAO) {
        this.papersDAO = papersDAO;
        this.tagsService = tagsService;
        this.jwtConfigure = jwtConfigure;
        this.userDAO = userDAO;
    }

    /**
     * 获取文章列表
     */
    @Override
    public List<Map<String, Object>> getAll() {
        return papersDAO.getAll();
    }

    /**
     * 删除文章
     */
    @Override
    public void removeById(String id) {
        papersDAO.removeById(id);
    }

    /**
     * 保存文章
     */
    @Override
    public void savePapers(HttpServletRequest request, PapersForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Papers entity = new Papers();
        entity.setContent(form.getContent());
        entity.setTitle(form.getTitle());
        List<Map<String, Object>> tagsList = tagsService.findByIds(form.getTagsList()).stream().map(BeanUtil::beanToMap).collect(Collectors.toList());
        entity.setTagsList(tagsList);
        entity.setFileList(form.getFileList());
        SysUserDTO userDTO = new SysUserDTO();
        BeanUtils.copyProperties(user, userDTO);
        entity.setUser(BeanUtil.beanToMap(userDTO));
        entity.setCreateTime(LocalDateTime.now());
        papersDAO.savePapers(entity);
    }
}
