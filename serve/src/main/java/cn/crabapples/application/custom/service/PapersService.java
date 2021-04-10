package cn.crabapples.application.custom.service;


import cn.crabapples.application.common.BaseService;
import cn.crabapples.application.custom.entity.Papers;
import cn.crabapples.application.custom.form.PapersForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO 
 *
 * @author Mr.He
 * 2021/4/10 14:41
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface PapersService extends BaseService {

    List<Papers> getAll();

    void removeById(String id);

    void savePapers(HttpServletRequest request , PapersForm form);
}
