package org.example.yaopin.dao.jpa;

import org.example.yaopin.entity.DatabaseBak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseBakRepository extends JpaRepository<DatabaseBak, String> {
}
