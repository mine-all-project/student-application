package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class IndexService {
    @Autowired
    UserDao userDao;
    public boolean login(HttpServletRequest request, User user){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("password",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<User> example = Example.of(user,exampleMatcher);
        Optional<User> optional = userDao.findOne(example);
        System.out.println(user);
        if(optional.isPresent()){
            System.err.println("登陆成功");
            request.getSession().setAttribute("user",optional.get());
            return true;
        }
        System.err.println("登陆失败");
        return false;
    }
}
