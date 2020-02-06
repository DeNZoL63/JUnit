package com.haulmont.example.order;

public class Order {
    private OrderStatus status;

    public Order() {
        status = OrderStatus.NEW;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
