package com.haulmont.example;

import com.haulmont.example.order.HttpClient;
import com.haulmont.example.order.Order;
import com.haulmont.example.order.OrderStatus;

import java.util.regex.Pattern;

public class OrderService {
    HttpClient client;

    public OrderService(HttpClient client) {
        this.client = client;
    }

    public boolean reserveOrder(Order order) {
        if (order.getStatus() == OrderStatus.RESERVED) {
            return true;
        }
        String response = client.executeRequest("localhost/v1/order/reserve", order);
        if (response.equals("success")) {
            order.setStatus(OrderStatus.RESERVED);
            return true;
        }
        order.setStatus(OrderStatus.CANCELLED);
        return false;
    }

}
