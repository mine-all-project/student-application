package cn.crabapples.application.common;

import cn.crabapples.application.common.utils.jwt.JwtConfigure;
import cn.crabapples.application.common.utils.jwt.JwtTokenUtils;
import cn.crabapples.application.system.dao.UserDAO;
import cn.crabapples.application.system.entity.SysUser;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO 系统服务基础方法
 *
 * @author Mr.He
 * 2021/4/9 1:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface BaseService {
    default SysUser getUserInfo(HttpServletRequest request, JwtConfigure configure, UserDAO userDAO, boolean isDebug) {
        String userId = "001";
        if (!isDebug) {
            final String authHeader = request.getHeader(configure.getAuthKey());
            Claims claims = JwtTokenUtils.parseJWT(authHeader, configure.getBase64Secret());
            userId = String.valueOf(claims.get("userId"));
        }
        System.err.println(userDAO.findById(userId));
        return userDAO.findById(userId);
    }
}
