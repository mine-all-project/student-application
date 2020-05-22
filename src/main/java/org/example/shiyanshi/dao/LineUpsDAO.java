package org.example.shiyanshi.dao;

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

//    public List<Purchases> getPurchasesListByStatus() {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return purchasesRepository.findByStatusAndDelFlag(sort, 0, 0);
//    }
//
//    public List<Purchases> getPurchasesListByStatusNot() {
//        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
//        return purchasesRepository.findByStatusNotAndDelFlag(sort, 0, 0);
//    }
//
//    public Purchases findById(String id) {
//        return purchasesRepository.findById(id).orElse(new Purchases());
//    }
//
//    public void saveData(Purchases purchases) {
//        purchasesRepository.saveAndFlush(purchases);
//    }
//
//    public void flagDelById(String id) {
//        Purchases purchases = purchasesRepository.findById(id).orElse(null);
//        assert purchases != null;
//        purchases.setDelFlag(1);
//        purchasesRepository.saveAndFlush(purchases);
//    }


}
