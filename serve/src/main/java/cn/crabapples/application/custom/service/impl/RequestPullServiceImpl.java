package cn.crabapples.application.custom.service.impl;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.custom.dao.RequestPullDAO;
import cn.crabapples.application.custom.dao.SubjectDAO;
import cn.crabapples.application.custom.dao.SubjectShareDAO;
import cn.crabapples.application.custom.service.RequestPullService;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


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
public class RequestPullServiceImpl implements RequestPullService {
    private final JwtConfigure jwtConfigure;
    private final SubjectDAO subjectDAO;
    private final UserDAO userDAO;
    private final SubjectShareDAO subjectShareDAO;
    private final RequestPullDAO requestPullDAO;
    @Value("${isDebug}")
    private boolean isDebug;

    public RequestPullServiceImpl(JwtConfigure jwtConfigure, SubjectDAO subjectDAO,
                                  UserDAO userDAO, SubjectShareDAO subjectShareDAO,
                                  RequestPullDAO requestPullDAO) {
        this.jwtConfigure = jwtConfigure;
        this.subjectDAO = subjectDAO;
        this.userDAO = userDAO;
        this.subjectShareDAO = subjectShareDAO;
        this.requestPullDAO = requestPullDAO;
    }

    @Override
    public void requestPull(HttpServletRequest request, String id) {
        SysUser user = getUserInfo(request,jwtConfigure,userDAO,isDebug);
        requestPullDAO.requestPull(user,id);
    }
}
