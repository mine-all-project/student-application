package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Order;
import org.example.application.custom.form.OrderForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService extends BaseService {
    List<Order> getAll(HttpServletRequest request);

//    List<Order> search(HttpServletRequest request,String keywords);

    Order save(HttpServletRequest request, OrderForm form);

    void deleteById(HttpServletRequest request,String id);

    void checkPass(HttpServletRequest request,String id);

    void checkFail(HttpServletRequest request,String id);

    List<Order> searchDate(HttpServletRequest request,String beginTime, String endTime);

    void allPass(HttpServletRequest request);
}
