package cn.crabapples.application.dao.jpa;

import cn.crabapples.application.entity.FileData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDataRepository extends JpaRepository<cn.crabapples.application.entity.FileData, String> {
    List<cn.crabapples.application.entity.FileData> findByKeywords(String keywords, Sort sort);

    List<FileData> findByIdIn(List<String> ids, Sort sort);
}
