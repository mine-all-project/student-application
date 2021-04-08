package cn.crabapples.application.custom.service;

import cn.crabapples.application.common.BaseService;
import cn.crabapples.application.custom.entity.RequestPull;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * TODO 
 *
 * @author Mr.He
 * 2021/4/9 1:49
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface RequestPullService extends BaseService {

    void requestPull(HttpServletRequest request, String id);

    List<RequestPull> requestPullList();

    void accept(String id);

    void unaccepted(String id);
}
