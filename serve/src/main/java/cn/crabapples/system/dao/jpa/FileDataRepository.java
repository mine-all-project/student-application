package cn.crabapples.system.dao.jpa;

import cn.crabapples.system.entity.FileData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, String> {
    List<FileData> findByKeywords(String keywords, Sort sort);

    List<FileData> findByIdIn(List<String> ids, Sort sort);
}
