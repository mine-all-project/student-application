package com.example.hzj.controller;


import com.example.hzj.entity.User;

public abstract class BaseHandle {

    public boolean checkUser(User user){
        if(user.getType()==1) {
            return true;
        }else{
            return false;
        }
    }

}
