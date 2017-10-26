package model.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.OrderDAO;
import model.entity.Order;
import model.entity.Periodical;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Provalov on 25.09.2017.
 */
public class OrderService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private OrderService() {

    }

    private static class Holder {
        static final OrderService INSTANCE = new OrderService();
    }

    public static OrderService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Order> getAllOrders() throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            OrderDAO orderDAO = daoFactory.createOrderDAO(connection);
            List<Order> orders = orderDAO.findAll();
            PeriodicalService service = PeriodicalService.getInstance();
            for (Order order : orders) {
                List<Periodical> oPeriodicals = service.getPeriodicalsByOrderId(order.getId());
                order.setPeriodicals(oPeriodicals);
            }
            return orders;
        }
    }

    public List<Order> getOrdersByClient(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            OrderDAO orderDAO = daoFactory.createOrderDAO(connection);
            return orderDAO.findByClientId(id);
        }
    }

    public long countOrderPrice(Order order, int discount) {
        long summary = 0;
        for (Periodical periodical : order.getPeriodicals()) {
            summary += periodical.getSubscriptionPrice();
        }
        long result = (summary * 100) - (discount * summary);
        return result / 100;
    }

    public int countDiscount(int month) {
        if (month < 0) {
            return 0;
        }
        int discount = 0;
        int maxDiscount = 50;
        if (month > 2) {
            discount += month * 5;
            if (discount > maxDiscount) {
                discount = maxDiscount;
            }
        }
        return discount;
    }

    public boolean createNewOrder(Order order) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            OrderDAO orderDAO = daoFactory.createOrderDAO(connection);
            connection.begin();
            int result = orderDAO.insert(order);
            orderDAO.insertOrderDetails(order);
            connection.commit();
            return result != 0;
        } finally {

        }
    }
}