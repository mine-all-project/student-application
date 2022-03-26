package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Order;
import org.example.application.custom.form.OrderForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService extends BaseService {
    List<Order> getAll();

    List<Order> search(String keywords);

    Order save(HttpServletRequest request, OrderForm form);

    void deleteById(String id);

    void checkPass(OrderForm form);

    void checkFail(OrderForm form);
}
