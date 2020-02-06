package com.haulmont.mock;

import com.haulmont.example.OrderServiceAdv;
import com.haulmont.example.order.HttpClient;
import com.haulmont.example.order.Order;
import com.haulmont.example.order.OrderStatus;
import com.haulmont.example.order.OrderUpdater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceAdvTest {
    @Mock
    private HttpClient client;
    @Mock
    private OrderUpdater orderUpdaterMock;
    @Spy
    private OrderUpdater orderUpdaterSpy;
    private OrderServiceAdv orderService;

    public void setUpMock() {
        orderService = new OrderServiceAdv(client, orderUpdaterMock);
        Mockito.doAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            OrderStatus status = invocation.getArgument(1);
            order.setStatus(status);
            return null;
        }).when(orderUpdaterMock).updateOrderStatus(any(), any());
    }

    public void setUpSpy() {
        orderService = new OrderServiceAdv(client, orderUpdaterSpy);
        Mockito.doNothing().when(orderUpdaterSpy).saveToDatabase(any());
    }

    @Test
    public void testReserveOrderOnSuccessMock() {
        setUpMock();
        Order order = new Order();
        Mockito.when(client.executeRequest(anyString(), any())).thenReturn("success");
        boolean result = orderService.reserveOrder(order);
        assertTrue(result);
        assertEquals(order.getStatus(), OrderStatus.RESERVED);
    }

    @Test
    public void testReserveOrderOnSuccessSpy() {
        setUpSpy();
        Order order = new Order();
        Mockito.when(client.executeRequest(anyString(), any())).thenReturn("success");
        boolean result = orderService.reserveOrder(order);
        assertTrue(result);
        assertEquals(order.getStatus(), OrderStatus.RESERVED);
    }


}
