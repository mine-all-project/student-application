package org.example.textreader.custom.dao.jpa;

import org.example.textreader.custom.entity.ReadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadInfoRepository extends JpaRepository<ReadInfo, String> {
    Optional<ReadInfo> findByIdAndStatus(String id, int readInfoSuccess);
}
