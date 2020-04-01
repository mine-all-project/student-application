package cn.crabapples.tuole.controller;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseHandle {
    public boolean checkSession(HttpServletRequest request) {
        return false;
    }
}