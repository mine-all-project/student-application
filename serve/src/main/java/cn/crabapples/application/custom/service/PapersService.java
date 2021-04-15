package cn.crabapples.application.custom.service;


import cn.crabapples.application.common.BaseService;
import cn.crabapples.application.custom.form.PapersForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 文章功能抽象接口（elasticsearch，暂未实现）
 */
public interface PapersService extends BaseService {

    List<Map<String, Object>> getAll();

    void removeById(String id);

    void savePapers(HttpServletRequest request , PapersForm form);
}
