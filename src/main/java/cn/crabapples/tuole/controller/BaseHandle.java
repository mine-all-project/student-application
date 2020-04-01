package cn.crabapples.tuole.controller;


import cn.crabapples.tuole.entity.User;

public abstract class BaseHandle {

    public boolean checkUser(User user){
        if(user.getType()==1) {
            return true;
        }else{
            return false;
        }
    }

}
