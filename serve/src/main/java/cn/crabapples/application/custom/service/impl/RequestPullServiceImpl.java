package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.dao.RequestPullDAO;
import cn.crabapples.application.custom.dao.SubjectDAO;
import cn.crabapples.application.custom.dao.SubjectShareDAO;
import cn.crabapples.application.custom.dao.jpa.SubjectShareRepository;
import cn.crabapples.application.custom.entity.RequestPull;
import cn.crabapples.application.custom.entity.SubjectShare;
import cn.crabapples.application.custom.service.RequestPullService;
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
 * 2021/4/9 1:49
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class RequestPullServiceImpl implements RequestPullService {
    private final JwtConfigure jwtConfigure;
    private final SubjectDAO subjectDAO;
    private final UserDAO userDAO;
    private final SubjectShareDAO subjectShareDAO;
    private final RequestPullDAO requestPullDAO;
    private final SubjectShareRepository subjectShareRepository;
    @Value("${isDebug}")
    private boolean isDebug;

    public RequestPullServiceImpl(JwtConfigure jwtConfigure, SubjectDAO subjectDAO,
                                  UserDAO userDAO, SubjectShareDAO subjectShareDAO,
                                  RequestPullDAO requestPullDAO,
                                  SubjectShareRepository subjectShareRepository) {
        this.jwtConfigure = jwtConfigure;
        this.subjectDAO = subjectDAO;
        this.userDAO = userDAO;
        this.subjectShareDAO = subjectShareDAO;
        this.requestPullDAO = requestPullDAO;
        this.subjectShareRepository = subjectShareRepository;
    }

    @Override
    public void requestPull(HttpServletRequest request, String id) {
        SysUser user = getUserInfo(request, jwtConfigure, userDAO, isDebug);
        requestPullDAO.requestPull(user, id);
    }

    @Override
    public List<RequestPull> requestPullList() {
        List<RequestPull> list = requestPullDAO.findAll();
        list.forEach(e -> {
            SysUserDTO userDTO = new SysUserDTO();
            BeanUtils.copyProperties(e.getSysUser(), userDTO);
            e.setUser(userDTO);
        });
        return list;
    }

    @Override
    public void accept(String id) {
        RequestPull requestPull = requestPullDAO.findById(id);
        requestPull.setStatus(0);
        SubjectShare share = subjectShareRepository.findBySubjectAndStatus(requestPull.getSubject(), 0).orElse(null);
        List<SysUser> userList = share.getShareUser();
        userList.add(requestPull.getSysUser());
        share.setShareUser(userList);
        requestPullDAO.save(requestPull);
    }

    @Override
    public void unaccepted(String id) {
        RequestPull requestPull = requestPullDAO.findById(id);
        requestPull.setStatus(2);
        requestPullDAO.save(requestPull);
    }
}
