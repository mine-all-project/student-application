package org.example.application.custom.dao.jpa;

import org.example.application.custom.entity.LostItems;
import org.example.application.system.entity.SysUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemsRepository extends JpaRepository<LostItems, String> {
    List<LostItems> findAllByTitleLike(String keywords, Sort sort);

    List<LostItems> findAllByPublisher(SysUser publisher, Sort sort);
}
