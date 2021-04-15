package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.common.utils.AssertUtils;
import cn.crabapples.application.custom.dao.jpa.RequestPullRepository;
import cn.crabapples.application.custom.entity.RequestPull;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.system.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 访问请求数据库交互实现类
 */
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
        RequestPull exist = requestPullRepository.findBySysUserAndSubjectAndDelFlag(user, subject, NOT_DEL);
        AssertUtils.isNull(exist, "请勿重复申请");
        RequestPull requestPull = new RequestPull(user, subject);
        requestPull.setStatus(1);
        requestPullRepository.saveAndFlush(requestPull);

    }

    public List<RequestPull> findAll() {
        return requestPullRepository.findAllByStatusAndDelFlag(1, NOT_DEL);
    }

    public RequestPull findById(String id) {
        return requestPullRepository.findByIdAndDelFlag(id, NOT_DEL);
    }

    public void save(RequestPull requestPull) {
        requestPullRepository.saveAndFlush(requestPull);
    }
}
