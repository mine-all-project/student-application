package com.example.demo.controller;


import com.example.demo.entity.User;

public abstract class BaseHandle {

    public boolean checkUser(User user){
        return user.getType() == 1;
    }

}
