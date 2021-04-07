package cn.crabapples.application.custom.service;

import cn.crabapples.application.common.BaseService;

import javax.servlet.http.HttpServletRequest;

public interface RequestPullService extends BaseService {

    void requestPull(HttpServletRequest request, String id);
}
