package cn.crabapples.yangjichang.dao;

import cn.crabapples.yangjichang.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO 销售信息持久层
 *
 * @author Mr.He
 * 2019/7/4 1422:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {

    List<Goods> findAllByKeyWord(String video);

}
