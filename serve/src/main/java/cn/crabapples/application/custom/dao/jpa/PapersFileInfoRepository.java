package cn.crabapples.application.custom.dao.jpa;

import cn.crabapples.application.custom.entity.PaperFileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 文章附件数据库交互
 */
@Repository
public interface PapersFileInfoRepository extends JpaRepository<PaperFileInfo, String> {
}
