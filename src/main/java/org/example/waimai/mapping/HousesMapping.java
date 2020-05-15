package org.example.waimai.mapping;

import org.example.waimai.entity.Houses;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousesMapping {
    List<Houses> findAll();

    List<Houses> findAllByTitle(String title);

    Houses getHousesById(String id);

    Integer removeHousesById(String id);

    Integer updateHousesInfo(Houses houses);

    Integer insertHousesInfo(Houses houses);

    List<Houses> findAllByUser(String userId);
}
