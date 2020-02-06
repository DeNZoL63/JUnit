package com.haulmont.mock;

import com.haulmont.example.OrderService;
import com.haulmont.example.order.HttpClient;
import com.haulmont.example.order.Order;
import com.haulmont.example.order.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private HttpClient client;
    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService(client);
    }

    @Test
    public void testReserveOrderOnSuccess() {
        Order order = new Order();
        Mockito.when(client.executeRequest(anyString(), any())).thenReturn("success");
        boolean result = orderService.reserveOrder(order);
        assertTrue(result);
        assertEquals(order.getStatus(), OrderStatus.RESERVED);
    }

    @Test
    public void testReserveOrderOnFail() {
        Order order = new Order();
        Mockito.when(client.executeRequest(anyString(), any())).thenReturn("fail");
        boolean result = orderService.reserveOrder(order);
        assertFalse(result);
        assertEquals(order.getStatus(), OrderStatus.CANCELLED);
    }

    @Test
    public void testIsExecutorMethodCalled() {
        Order order = new Order();
        Mockito.when(client.executeRequest(anyString(), any())).thenReturn("success");
        orderService.reserveOrder(order);
        Mockito.verify(client, Mockito.times(1)).executeRequest(anyString(), any());
    }

    @Test
    public void testIsExecutorMethodNeverCalled() {
        Order order = new Order();
        order.setStatus(OrderStatus.RESERVED);
        orderService.reserveOrder(order);
        Mockito.verify(client, Mockito.never()).executeRequest(anyString(), any());
    }

    @Test
    public void testIsExecutorMethodCalledWithRightParameters() {
        Order order = new Order();
        Mockito.when(client.executeRequest(anyString(), any())).thenReturn("success");
        orderService.reserveOrder(order);
        Mockito.verify(client, Mockito.times(1)).executeRequest("localhost/v1/order/reserve", order);
    }

}
