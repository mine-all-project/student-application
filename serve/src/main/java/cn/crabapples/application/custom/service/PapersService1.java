package cn.crabapples.application.custom.service;


import cn.crabapples.application.common.BaseService;
import cn.crabapples.application.custom.entity.Papers1;
import cn.crabapples.application.custom.form.PapersForm1;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文章功能抽象接口（mysql）
 */
public interface PapersService1 extends BaseService {

    List<Papers1> getAll();

    void removeById(String id);

    void savePapers(HttpServletRequest request , PapersForm1 form);
}
