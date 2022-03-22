package org.example.application.custom.dao;

import org.example.application.common.ApplicationException;
import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.mybatis.PaperMapper;
import org.example.application.custom.entity.Paper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PaperDAO extends BaseDAO {
    private final PaperMapper paperMapper;

    public PaperDAO(PaperMapper paperMapper) {
        this.paperMapper = paperMapper;
    }

    public void addPaper(Paper entity) {
        entity.setId(UUID.randomUUID().toString());
        paperMapper.insert(entity);
    }
    public void updatePaper(Paper entity) {
        paperMapper.updateByPrimaryKeySelective(entity);
    }

    public Paper findPaperById(String id) {
        return paperMapper.selectByPrimaryKey(id);
    }

    public void deletePaperById(String id) {
        paperMapper.deleteByPrimaryKey(id);
    }


    public List<Paper> getAll() {
        throw new ApplicationException("暂未实现");
    }

    public List<Paper> getAllByTypeId(String pid) {
        throw new ApplicationException("暂未实现");
    }
}
