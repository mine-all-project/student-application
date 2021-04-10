package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.common.utils.mail.MailUtils;
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

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final MailUtils mailUtils;
    @Value("${isDebug}")
    private boolean isDebug;

    public PapersServiceImpl1(PapersDAO1 papersDAO, TagsService tagsService,
                              PaperFileInfoDAO paperFileInfoDAO, JwtConfigure jwtConfigure,
                              UserDAO userDAO, MailUtils mailUtils) {
        this.papersDAO = papersDAO;
        this.tagsService = tagsService;
        this.paperFileInfoDAO = paperFileInfoDAO;
        this.jwtConfigure = jwtConfigure;
        this.userDAO = userDAO;
        this.mailUtils = mailUtils;
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
        String id = form.getId();
        Papers1 entity = papersDAO.findById(id);
        if (entity == null) {
            entity = new Papers1();
        }
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
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
        Papers1 papers = papersDAO.savePapers(entity);
        noticeUser(id, papers, tagsList);
    }

    void noticeUser(String id, Papers1 papers, List<Tags> tagsList) {
        List<String> send = new ArrayList<>();
        if (id.length() <= 0) {
            List<SysUser> userList = userDAO.findAll();
            userList.forEach(user -> {
                AtomicBoolean isSend = new AtomicBoolean(false);
                user.getTags().forEach(tags -> {
                    tagsList.forEach(e -> {
                        if (e.getId().equals(tags.getId())) {
                            isSend.set(true);
                        }
                    });
                });
                if (isSend.get()) {
                    send.add(user.getMail());
                }
            });
            if (send.size() > 0) {
                try {
                    Session session = mailUtils.initSession();
                    String[] target = new String[send.size()];
                    MimeMessage message = mailUtils.initMessage(session, "科研项目通知", send.toArray(target));
                    Multipart multipart = new MimeMultipart();
                    String content = "有一个新的科研项目" +
                            "[" + papers.getTitle() + "]" +
                            "与你设置的标签匹配成功，您可以登录查看详细信息";
                    mailUtils.addMailContentText(multipart, content);
                    message.setContent(multipart);
                    mailUtils.sendEmail(session, message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
