package com.example.hzj.service;

import com.example.hzj.dao.MessageDao;
import com.example.hzj.dao.MineInfoDao;
import com.example.hzj.dao.UserDao;
import com.example.hzj.entity.Message;
import com.example.hzj.entity.MineInfo;
import com.example.hzj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    MineInfoDao mineInfoDao;
    @Autowired
    MessageDao messageDao;
    public User getMineInfoList(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<MineInfo> mineInfos = new ArrayList<>();
        Optional<User> optional = userDao.findById(user.getId());
        User _thisUser = null;
//        Optional<User> optional = userDao.findById("1");
        if(optional.isPresent()){
            _thisUser = optional.get();
        }
        return _thisUser;
    }

    public MineInfo getMineInfoById(String id) {
        Optional<MineInfo> optional = mineInfoDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public List<User> getOrgsList() {
        User user = new User();
        user.setType(2);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<User> example = Example.of(user,exampleMatcher);
        return userDao.findAll(example);
    }

    public void saveMessage(Message message) {
        messageDao.saveAndFlush(message);
    }

    public List<Message> getMessageList(HttpServletRequest request) {
        User _this = (User) request.getSession().getAttribute("user");
        Message message = new Message();
//        message.setToId(_this.getId());
        message.setToId("2");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("toId",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Message> example = Example.of(message,exampleMatcher);
        return messageDao.findAll(example);
    }
}