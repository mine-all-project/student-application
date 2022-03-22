package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.mybatis.PaperTypeMapper;
import org.example.application.custom.entity.PaperType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PaperTypeDAO extends BaseDAO {
    private final PaperTypeMapper paperTypeMapper;

    public PaperTypeDAO(PaperTypeMapper paperTypeMapper) {
        this.paperTypeMapper = paperTypeMapper;
    }

    public List<PaperType> getAllTypes() {
        return paperTypeMapper.findAll();
    }

    public void addPaperType(PaperType type) {
        type.setId(UUID.randomUUID().toString());
        paperTypeMapper.insert(type);
    }
    public void updatePaperType(PaperType type) {
        paperTypeMapper.updateByPrimaryKeySelective(type);
    }

    public PaperType findPaperTypeById(String id) {
        return paperTypeMapper.selectByPrimaryKey(id);
    }

    public void deletePaperTypeById(String id) {
        paperTypeMapper.deleteByPrimaryKey(id);
    }


}
