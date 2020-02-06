package com.haulmont.example;

import com.haulmont.example.order.*;

public class OrderServiceAdv {
    OrderUpdater orderUpdater;
    HttpClient client;

    public OrderServiceAdv(HttpClient client, OrderUpdater orderUpdater) {
        this.orderUpdater = orderUpdater;
        this.client = client;
    }

    public boolean reserveOrder(Order order) {
        if (order.getStatus() == OrderStatus.RESERVED) {
            return true;
        }
        String response = client.executeRequest("localhost/v1/order/reserve", order);
        if (response.equals("success")) {
            orderUpdater.updateOrderStatus(order, OrderStatus.RESERVED);
            return true;
        }
        orderUpdater.updateOrderStatus(order, OrderStatus.CANCELLED);
        return false;
    }

    public Order loadOrderFromDb(){
        return OrderLoader.loadOrder();
    }

}
