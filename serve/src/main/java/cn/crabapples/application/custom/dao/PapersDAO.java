package cn.crabapples.application.custom.dao;

import cn.crabapples.application.common.ApplicationException;
import cn.crabapples.application.common.BaseDAO;
import cn.crabapples.application.custom.entity.Papers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/4/10 15:04
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Component
public class PapersDAO extends BaseDAO {

    public List<Papers> getAll() {
        throw new ApplicationException("暂未实现");
    }

    public void removeById(String id) {
        System.err.println(id);
        throw new ApplicationException("暂未实现");
    }

    public void savePapers(Papers entity) {
        System.err.println(entity);
        throw new ApplicationException("暂未实现");
    }
}
