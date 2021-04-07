package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.custom.dao.jpa.RequestPullRepository;
import cn.crabapples.application.custom.entity.RequestPull;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

@Component
public class RequestPullDAO extends BaseDAO {
private final RequestPullRepository requestPullRepository;
private final SubjectDAO subjectDAO;

    public RequestPullDAO(RequestPullRepository requestPullRepository, SubjectDAO subjectDAO) {
        this.requestPullRepository = requestPullRepository;
        this.subjectDAO = subjectDAO;
    }

    public void requestPull(SysUser user, String id) {
        Subject subject = subjectDAO.findById(id);
        RequestPull requestPull = new RequestPull(user,subject);
        requestPull.setStatus(1);
        requestPullRepository.saveAndFlush(requestPull);

    }
}
