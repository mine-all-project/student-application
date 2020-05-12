package org.example.yaopin.dao.jpa;

import org.example.yaopin.entity.Storages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoragesRepository extends JpaRepository<Storages, String> {

}
