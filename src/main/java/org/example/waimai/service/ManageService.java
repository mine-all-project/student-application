package org.example.waimai.service;

import org.example.waimai.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface ManageService extends BaseService{
    List<SysUser> getUserList();

    void changeStatus(String id);

    void removeUserById(String id);

    void changePassword(Map map);
}
