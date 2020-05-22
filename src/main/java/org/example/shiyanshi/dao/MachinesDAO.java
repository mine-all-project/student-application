package org.example.shiyanshi.dao;


import org.example.shiyanshi.config.base.BaseDAO;
import org.example.shiyanshi.dao.jpa.MachinesRepository;
import org.example.shiyanshi.entity.Machines;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MachinesDAO extends BaseDAO {
    private final MachinesRepository machinesRepository;

    public MachinesDAO(MachinesRepository machinesRepository) {
        this.machinesRepository = machinesRepository;
    }

    public List<Machines> getAll() {
        Sort sort = Sort.by(Sort.Order.asc("createTime"));
        return machinesRepository.findAll(sort);
    }

    public void saveData(Machines machines) {
        machinesRepository.saveAndFlush(machines);
    }

    public Machines findById(String id) {
        return machinesRepository.findById(id).orElse(new Machines());
    }

    public List<Machines> findAll() {
        Sort sort = Sort.by(Sort.Order.asc("createTime"));
        PageRequest page = PageRequest.of(pageIndex, pageSize, sort);
        return machinesRepository.findAll(sort);
    }

    public void delById(String id) {
        machinesRepository.deleteById(id);
    }
}
