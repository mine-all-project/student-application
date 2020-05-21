package org.example.shiyanshi.dao;

import org.example.shiyanshi.dao.jpa.RoomsRepository;
import org.example.shiyanshi.entity.Rooms;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/*
 * 预约DAO
 */
public class RoomsDAO {
    private final RoomsRepository roomsRepository;

    public RoomsDAO(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public Rooms saveData(Rooms rooms) {
        return roomsRepository.saveAndFlush(rooms);
    }

    public Rooms findDataById(String id) {
        return roomsRepository.findById(id).orElse(null);
    }

    public List<Rooms> getAllByFlag() {
        return roomsRepository.findAllByDelFlag(0);
    }
}
