package org.example.application.custom.dao;

import org.example.application.common.BaseDAO;
import org.example.application.custom.dao.jpa.PersonRepository;
import org.example.application.custom.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO extends BaseDAO {
    private final PersonRepository personRepository;

    public PersonDAO(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll(desByCreateTime);
    }

    public List<Person> search(String name) {
        return personRepository.findAllByNameLike(name, desByCreateTime);
    }

    public Person save(Person entity) {
        return personRepository.saveAndFlush(entity);
    }

    public void deleteById(String id) {
        personRepository.deleteById(id);
    }

    public Person getById(String id) {
        return personRepository.findById(id).orElse(null);
    }
}
