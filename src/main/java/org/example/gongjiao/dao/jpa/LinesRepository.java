package org.example.gongjiao.dao.jpa;

import org.example.gongjiao.entity.Linees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinesRepository extends JpaRepository<Linees, String> {

}
