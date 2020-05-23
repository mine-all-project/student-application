package org.example.shiyanshi.dao.jpa;


import org.example.shiyanshi.entity.Machines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachinesRepository extends JpaRepository<Machines, String> {
}
