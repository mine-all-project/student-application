package org.example.shiyanshi.dao.jpa;


import org.example.shiyanshi.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomsRepository extends JpaRepository<Rooms, String> {
    List<Rooms> findAllByDelFlag(int i);
}
