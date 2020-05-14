package org.example.shiyanshi.dao;

import org.example.shiyanshi.dao.jpa.DatabaseBakRepository;
import org.example.shiyanshi.entity.DatabaseBak;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseBakDAO {
    private final DatabaseBakRepository databaseBakRepository;

    public DatabaseBakDAO(DatabaseBakRepository databaseBakRepository) {
        this.databaseBakRepository = databaseBakRepository;
    }

    public List<DatabaseBak> getAll() {
        return databaseBakRepository.findAll();
    }

    public void saveAndFlush(DatabaseBak bak) {
        databaseBakRepository.saveAndFlush(bak);
    }

    public void deleteById(String id) {
        databaseBakRepository.deleteById(id);
    }

    public DatabaseBak findById(String id) {
        return databaseBakRepository.findById(id).orElse(null);
    }
}
