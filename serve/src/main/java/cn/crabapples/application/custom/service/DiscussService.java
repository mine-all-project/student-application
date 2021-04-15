package cn.crabapples.application.custom.service;


import cn.crabapples.application.common.BaseService;
import cn.crabapples.application.custom.entity.Discuss;
import cn.crabapples.application.custom.form.DiscussForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论功能抽象接口
 */
public interface DiscussService extends BaseService {

    void saveDiscuss(HttpServletRequest request, DiscussForm form);

    List<Discuss> getAll(String subjectId);

    void removeById(String id);
}
