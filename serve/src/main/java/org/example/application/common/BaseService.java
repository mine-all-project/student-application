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
        return userDAO.findById(userId);
    }

    default void checkOrderAuth(HttpServletRequest request, JwtConfigure configure, UserDAO userDAO) {
        String authHeader = request.getHeader(configure.getAuthKey());
        Claims claims = JwtTokenUtils.parseJWT(authHeader, configure.getBase64Secret());
        String userId = String.valueOf(claims.get("userId"));
        SysUser sysUser = userDAO.findById(userId);
        if (sysUser.getOrderStatus() == DIC.UN_AUTH) {
            throw new ApplicationException("暂无权限进行相关操作");
        }
    }

    default void checkOrderCountAuth(HttpServletRequest request, JwtConfigure configure, UserDAO userDAO) {
        String authHeader = request.getHeader(configure.getAuthKey());
        Claims claims = JwtTokenUtils.parseJWT(authHeader, configure.getBase64Secret());
        String userId = String.valueOf(claims.get("userId"));
        SysUser sysUser = userDAO.findById(userId);
        if (sysUser.getOrderCountStatus() == DIC.UN_AUTH) {
            throw new ApplicationException("暂无权限进行相关操作");
        }
    }
}
