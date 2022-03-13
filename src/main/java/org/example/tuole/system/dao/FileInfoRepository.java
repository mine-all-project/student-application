package org.example.tuole.system.dao;

import org.example.tuole.system.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, String> {

    FileInfo saveAndFlush(FileInfo sysFiles);
}
