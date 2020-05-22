package org.example.shiyanshi.dao;

import org.example.shiyanshi.dao.jpa.RoomsRepository;
import org.example.shiyanshi.entity.Rooms;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/*
 * 实验室DAO
 */
public class RoomsDAO extends BaseDAO {
    private final RoomsRepository roomsRepository;

    public RoomsDAO(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public List<Rooms> getRoomsList() {
        return roomsRepository.findAll();
    }

    public void saveData(Rooms rooms) {
        roomsRepository.saveAndFlush(rooms);
    }
}
