package org.example.application.common;

import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.common.utils.jwt.JwtTokenUtils;
import org.example.application.system.dao.UserDAO;
import org.example.application.system.entity.SysUser;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统服务基础方法
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

    default void checkPublishStatus(BaseEntity entity, HttpServletRequest request, JwtConfigure configure, UserDAO userDAO, boolean isDebug) {
        if (entity.getId() == null) {
            String userId = "001";
            if (!isDebug) {
                final String authHeader = request.getHeader(configure.getAuthKey());
                Claims claims = JwtTokenUtils.parseJWT(authHeader, configure.getBase64Secret());
                userId = String.valueOf(claims.get("userId"));
            }
            SysUser sysUser = userDAO.findById(userId);
            if (sysUser.getPublishStatus() == DIC.CHECK_FAIL) {
                throw new ApplicationException("您已被禁言");
            }
        }
    }

    default void checkTalkStatus(HttpServletRequest request, JwtConfigure configure, UserDAO userDAO, boolean isDebug) {
        String userId = "001";
        if (!isDebug) {
            final String authHeader = request.getHeader(configure.getAuthKey());
            Claims claims = JwtTokenUtils.parseJWT(authHeader, configure.getBase64Secret());
            userId = String.valueOf(claims.get("userId"));
        }
        SysUser sysUser = userDAO.findById(userId);
        if (sysUser.getTalkStatus() == DIC.CHECK_FAIL) {
            throw new ApplicationException("您已被禁言");
        }
    }
}
