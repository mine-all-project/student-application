package org.example.application.custom.service;

import org.example.application.common.BaseService;
import org.example.application.custom.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService extends BaseService {

    Order saveOrder(HttpServletRequest request,Order entity);

    List<Order> getOrderList(HttpServletRequest request);

    List<Order> getAllOrder(HttpServletRequest request);

    Order getOrderById(String id);

    void deleteOrder(String id);

    Order saveOrderNote(HttpServletRequest request, String id, String note);
}
