package org.example.shiyanshi.config.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.shiyanshi.config.ApplicationException;
import org.example.shiyanshi.dao.SysUserDAO;
import org.example.shiyanshi.entity.SysUser;
import org.example.shiyanshi.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

public interface BaseService {

    default void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    default String getFilePath(HttpServletRequest request, String filePath, String virtualPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        FileUtils fileUtils = new FileUtils(filePath, virtualPath);
        return fileUtils.saveFile(multipartFile);
    }

    default SysUser getUserInfo(boolean isDebug, SysUserDAO sysUserDAO) {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        String username;
        if (user == null) {
            if (isDebug) {
                username = "admin";
            } else {
                throw new ApplicationException("用户未登录");
            }
        } else {
            username = user.getUsername();
        }
        return sysUserDAO.findByUsername(username);
    }
}
