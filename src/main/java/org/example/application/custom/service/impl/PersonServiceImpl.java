package org.example.application.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.application.common.ApplicationException;
import org.example.application.common.utils.jwt.JwtConfigure;
import org.example.application.custom.dao.PersonDAO;
import org.example.application.custom.entity.Person;
import org.example.application.custom.form.MoneyForm;
import org.example.application.custom.form.PersonForm;
import org.example.application.custom.service.PersonService;
import org.example.application.system.dao.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonDAO personDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final UserDAO userDAO;
    private final JwtConfigure jwtConfigure;

    public PersonServiceImpl(PersonDAO personDAO, UserDAO userDAO, JwtConfigure jwtConfigure) {
        this.personDAO = personDAO;
        this.userDAO = userDAO;
        this.jwtConfigure = jwtConfigure;
    }

    @Override
    public List<Person> getAll(HttpServletRequest request) {
        checkOrderCountAuth(request, jwtConfigure, userDAO);
        return personDAO.getAll();
    }

    @Override
    public List<Person> search(HttpServletRequest request, String keywords) {
        checkOrderCountAuth(request, jwtConfigure, userDAO);
        return personDAO.search(keywords);
    }

    @Override
    public Person save(HttpServletRequest request, PersonForm form) {
        checkOrderCountAuth(request, jwtConfigure, userDAO);
        Person entity = form.toEntity();
        return personDAO.save(entity);
    }

    @Override
    public void deleteById(HttpServletRequest request, String id) {
        checkOrderCountAuth(request, jwtConfigure, userDAO);
        personDAO.deleteById(id);
    }

    @Override
    public void moneyPlus(HttpServletRequest request, MoneyForm form) {
        checkOrderCountAuth(request, jwtConfigure, userDAO);
        Person person = personDAO.getById(form.getId());
        if (person == null) {
            throw new ApplicationException("用户不存在");
        }
        BigDecimal money = person.getMoney();
        person.setMoney(money.add(form.getMoneyPlus()));
        personDAO.save(person);
    }

    @Override
    public void moneyReduce(HttpServletRequest request, MoneyForm form) {
        checkOrderCountAuth(request, jwtConfigure, userDAO);
        Person person = personDAO.getById(form.getId());
        if (person == null) {
            throw new ApplicationException("用户不存在");
        }
        BigDecimal money = person.getMoney();
        BigDecimal decimal = money.subtract(form.getMoneyReduce());
        if (decimal.compareTo(BigDecimal.ZERO) < 0) {
            throw new ApplicationException("余额不足");
        }
        person.setMoney(decimal);
        personDAO.save(person);
    }
}
