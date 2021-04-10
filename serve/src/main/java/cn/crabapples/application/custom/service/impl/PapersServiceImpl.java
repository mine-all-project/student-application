package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.dao.PapersDAO;
import cn.crabapples.application.custom.entity.Papers;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.PapersForm;
import cn.crabapples.application.custom.service.PapersService;
import cn.crabapples.application.custom.service.TagsService;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 14:58
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
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

    @Override
    public List<Papers> getAll() {
        return papersDAO.getAll();
    }

    @Override
    public void removeById(String id) {
        papersDAO.removeById(id);
    }

    @Override
    public void savePapers(HttpServletRequest request, PapersForm form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Papers entity = new Papers();
        entity.setContent(form.getContent());
        entity.setTitle(form.getTitle());
        List<Tags> tags = tagsService.findByIds(form.getTagsList());
        entity.setTagsList(tags);
        entity.setFileList(form.getFileList());
        SysUserDTO userDTO = new SysUserDTO();
        BeanUtils.copyProperties(user, userDTO);
        entity.setUser(userDTO);
        papersDAO.savePapers(entity);
    }
}
