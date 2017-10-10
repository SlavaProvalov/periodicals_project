package model.service;

import model.entity.Order;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class OrderServiceTest extends Mockito {
    OrderService service = OrderService.getInstance();

    @Test
    public void getAllOrders_normal() throws Exception {
        List<Order> orders = service.getAllOrders();
        assertTrue(orders.size() > 0);

    }

    @Test
    public void getOrdersByClient_normal() throws Exception {
        List<Order> orders = service.getOrdersByClient(7);
        for (Order order : orders) {
            assertTrue(order.getClientId() == 7);
        }
    }



}