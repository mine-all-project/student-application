package cn.crabapples.hxy.controller;


import cn.crabapples.hxy.entity.User;

public abstract class BaseHandle {

    public boolean checkUser(User user){
        if(user.getType()==1) {
            return true;
        }else{
            return false;
        }
    }

}
