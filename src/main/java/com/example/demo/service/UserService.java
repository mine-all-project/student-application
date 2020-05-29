package com.example.demo.service;

import com.example.demo.dao.MessageDao;
import com.example.demo.dao.MineInfoDao;
import com.example.demo.dao.OrgInfoDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Message;
import com.example.demo.entity.MineInfo;
import com.example.demo.entity.OrgInfo;
import com.example.demo.entity.User;
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
    OrgInfoDao orgInfoDao;
    @Autowired
    MessageDao messageDao;
    public User getMineInfoList(HttpServletRequest request){
        User _this = (User) request.getSession().getAttribute("user");
        List<MineInfo> mineInfos = new ArrayList<>();
        User _thisUser = null;
        Optional<User> optional = userDao.findById(_this.getId());
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

    public List<User> getUserList(int type) {
        User user = new User();
        user.setType(type);
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
        message.setToId(_this.getId());
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("toId",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Message> example = Example.of(message,exampleMatcher);
        return messageDao.findAll(example);
    }

    public void saveUser(String mineInfoId, User user, MineInfo mineInfo) {
        User _this = userDao.findById(user.getId()).get();
        user.setType(_this.getType());
        user.setBirthday(_this.getBirthday());
        user.setUsername(_this.getUsername());
        mineInfo.setId(mineInfoId);
        user.setMineInfo(mineInfo);
        mineInfoDao.saveAndFlush(user.getMineInfo());
        userDao.saveAndFlush(user);
    }
    public void saveUser(String orgInfoId, User user, OrgInfo orgInfo) {
        User _this = userDao.findById(user.getId()).get();
        user.setType(_this.getType());
        user.setBirthday(_this.getBirthday());
        user.setUsername(_this.getUsername());
        orgInfo.setId(orgInfoId);
        user.setOrgInfo(orgInfo);
        orgInfoDao.saveAndFlush(user.getOrgInfo());
        userDao.saveAndFlush(user);
    }
}
