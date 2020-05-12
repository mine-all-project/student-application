package org.example.yaopin.dao.jpa;

import org.example.yaopin.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, String> {

}
