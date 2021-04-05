package cn.crabapples.test.service.impl;

import cn.crabapples.system.entity.SysUser;
import cn.crabapples.test.dao.UserRepositoryTest;
import cn.crabapples.test.service.UserServiceTest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO 用户相关服务测试
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
public class UserServiceImplTest implements UserServiceTest {
    private UserRepositoryTest userRepositoryTest;

    public UserServiceImplTest(UserRepositoryTest userRepositoryTest) {
        this.userRepositoryTest = userRepositoryTest;
    }

    @Override
    public List<SysUser> findByHQL(String name) {
        return userRepositoryTest.findByHQL(name);
    }

    @Override
    public List<SysUser> findBySQL(String name) {
        return userRepositoryTest.findBySQL(name);
    }
}
