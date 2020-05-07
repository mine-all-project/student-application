package org.example.fangwuzulin.service.impl;

import org.example.fangwuzulin.config.ApplicationConfigure;
import org.example.fangwuzulin.mapping.SysUserMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(ManageServiceImpl.class);
    private final String salt;

    private final SysUserMapping sysUserMapping;

    public ManageServiceImpl(ApplicationConfigure applicationConfigure,
                             SysUserMapping sysUserMapping
    ) {
        this.salt = applicationConfigure.SALT;
        this.sysUserMapping = sysUserMapping;
    }


//
//    private String createCheckCode(String userKey) {
//        String redisKey = CODE_KEY + userKey;
//        String code = redisTemplate.opsForValue().get(redisKey);
//        if (StringUtils.isEmpty(code)) {
//            StringBuilder newCode = new StringBuilder();
//            Random random = new Random();
//            for (int i = 0; i < 6; i++) {
//                newCode.append(random.nextInt(10));
//            }
//            code = newCode.toString();
//            redisTemplate.opsForValue().set(redisKey, code, 10, TimeUnit.MINUTES);
//        }
//        return code;
//    }
//
//    @Override
//    public List<SysUser> getUserList() {
//        return sysUserRepository.findAll();
//    }
//
//    @Override
////    @RequiresPermissions("manage")
//    public void changeStatus(String id) {
//        SysUser sysUser = sysUserRepository.findById(id).orElse(null);
//        assert sysUser != null;
//        sysUser.setStatus(Math.abs(sysUser.getStatus() - 1));
//        sysUserRepository.save(sysUser);
//    }
//
//    @Override
////    @RequiresPermissions("manage")
//    public void removeUserById(String id) {
//        SysUser sysUser = sysUserRepository.findById(id).orElse(null);
//        assert sysUser != null;
//        if(sysUser.isAdmin()){
//            throw new ApplicationException("不能删除管理员");
//        }
//        sysUserRepository.delete(sysUser);
//    }
//
//
//    @Override
//    public void savePassword(Map map) {
//        Subject subject = SecurityUtils.getSubject();
//        SysUser sysUser = (SysUser) subject.getPrincipal();
//        String password = sysUser.getPassword();
//        String oldPassword = new Md5Hash(map.get("password"), salt).toString();
//        String newPassword = new Md5Hash(map.get("newPassword"), salt).toString();
//        String rePassword = new Md5Hash(map.get("rePassword"), salt).toString();
//        System.err.println(password);
//        System.err.println(newPassword);
//        System.err.println(rePassword);
//        if(!newPassword.equals(rePassword)){
//            throw new ApplicationException("两次密码不相同");
//        }
//        if(!password.equals(oldPassword)){
//            throw new ApplicationException("原密码错误");
//        }
//        sysUser.setPassword(newPassword);
//        sysUserRepository.save(sysUser);
//    }
}
