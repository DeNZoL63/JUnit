package com.haulmont.example.order;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class OrderUpdater {
    public void updateOrderStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        saveToDatabase(order);
    }

    public void saveToDatabase(Order order) {
        throw new NotImplementedException();
    }
}
