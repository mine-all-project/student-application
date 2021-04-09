package cn.crabapples.application.custom.service;


import cn.crabapples.application.common.BaseService;
import cn.crabapples.application.custom.entity.Discuss;
import cn.crabapples.application.custom.form.DiscussForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/9 1:59
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface DiscussService extends BaseService {

    void saveDiscuss(HttpServletRequest request, DiscussForm form);

    List<Discuss> getAll(String subjectId);

    void removeById(String id);
}
