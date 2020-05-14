package org.example.yaopin.dao;

import org.example.yaopin.dao.jpa.DatabaseBakRepository;
import org.example.yaopin.entity.DatabaseBak;
import org.springframework.beans.factory.annotation.Autowired;
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
