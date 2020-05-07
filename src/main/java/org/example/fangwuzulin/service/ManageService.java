package org.example.fangwuzulin.service;

import org.example.fangwuzulin.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface ManageService {
    List<SysUser> getUserList();

    void changeStatus(String id);

    void removeUserById(String id);

    void changePassword(Map map);
}
