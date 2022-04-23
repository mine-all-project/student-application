package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Person;
import org.example.application.custom.form.MoneyForm;
import org.example.application.custom.form.PersonForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PersonService extends BaseService {
    List<Person> getAll(HttpServletRequest request);

    List<Person> search(HttpServletRequest request,String keywords);

    Person save(HttpServletRequest request, PersonForm form);

    void deleteById(HttpServletRequest request,String id);

    void moneyPlus(HttpServletRequest request,MoneyForm form);

    void moneyReduce(HttpServletRequest request,MoneyForm form);
}
