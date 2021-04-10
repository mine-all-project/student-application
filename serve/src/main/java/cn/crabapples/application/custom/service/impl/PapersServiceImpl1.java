package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.dao.PaperFileInfoDAO;
import cn.crabapples.application.custom.dao.PapersDAO1;
import cn.crabapples.application.custom.entity.PaperFileInfo;
import cn.crabapples.application.custom.entity.Papers1;
import cn.crabapples.application.custom.entity.Tags;
import cn.crabapples.application.custom.form.PapersForm1;
import cn.crabapples.application.custom.service.PapersService1;
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
public class PapersServiceImpl1 implements PapersService1 {
    private final PapersDAO1 papersDAO;
    private final TagsService tagsService;
    private final PaperFileInfoDAO paperFileInfoDAO;
    private final JwtConfigure jwtConfigure;
    private final UserDAO userDAO;
    @Value("${isDebug}")
    private boolean isDebug;

    public PapersServiceImpl1(PapersDAO1 papersDAO, TagsService tagsService,
                              PaperFileInfoDAO paperFileInfoDAO, JwtConfigure jwtConfigure,
                              UserDAO userDAO) {
        this.papersDAO = papersDAO;
        this.tagsService = tagsService;
        this.paperFileInfoDAO = paperFileInfoDAO;
        this.jwtConfigure = jwtConfigure;
        this.userDAO = userDAO;
    }

    @Override
    public List<Papers1> getAll() {
        return papersDAO.getAll();
    }

    @Override
    public void removeById(String id) {
        papersDAO.removeById(id);
    }

    @Override
    public void savePapers(HttpServletRequest request, PapersForm1 form) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        Papers1 entity = new Papers1();
        BeanUtils.copyProperties(form, entity);
        List<Tags> tagsList = tagsService.findByIds(form.getTagsList());
        entity.setTagsList(tagsList);
        SysUserDTO userDTO = new SysUserDTO();
        BeanUtils.copyProperties(user, userDTO);
        entity.setCreateBy(user);
        entity.setUser(userDTO);
        List<PaperFileInfo> fileList = form.getFileList();
        paperFileInfoDAO.saveAll(fileList);
        entity.setFileList(fileList);
        papersDAO.savePapers(entity);
    }
}
