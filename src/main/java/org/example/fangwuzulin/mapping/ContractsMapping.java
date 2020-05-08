package org.example.fangwuzulin.mapping;

import org.example.fangwuzulin.entity.Contracts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractsMapping {
    Integer insertContracts(Contracts contracts);

    Integer updateContracts(Contracts contracts);

    Contracts findById(String id);

    List<Contracts> findAllContracts();

    Integer removeContractsById(String id);
}
