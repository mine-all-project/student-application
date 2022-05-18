package com.xbb.cases.controller;

import com.xbb.cases.constant.SessionConstant;
import com.xbb.cases.entity.Cases;
import com.xbb.cases.entity.User;

import javax.servlet.http.HttpSession;

public class DataFilter {
    protected boolean filterEncypt(HttpSession session, Cases cases) {
        User u = (User) session.getAttribute(SessionConstant.KEY_USER);
        return u.getId().equals(cases.getCreateById());
    }
}
