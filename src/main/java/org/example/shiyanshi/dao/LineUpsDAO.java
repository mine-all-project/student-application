package org.example.shiyanshi.dao;

import org.example.shiyanshi.config.base.BaseDAO;
import org.example.shiyanshi.dao.jpa.LineUpsRepository;
import org.example.shiyanshi.entity.LineUps;
import org.example.shiyanshi.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/*
 * 预约DAO
 */
public class LineUpsDAO extends BaseDAO {
    private final LineUpsRepository lineUpsRepository;

    public LineUpsDAO(LineUpsRepository lineUpsRepository) {
        this.lineUpsRepository = lineUpsRepository;
    }

    public List<LineUps> findAllByUser(SysUser user) {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        return lineUpsRepository.findAllByUser(sort, user);
    }

    public void saveData(LineUps lineUps) {
        lineUpsRepository.saveAndFlush(lineUps);
    }

    public LineUps findById(String id) {
        return lineUpsRepository.findById(id).orElse(new LineUps());
    }
}
