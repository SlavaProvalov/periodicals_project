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
            return orderDAO.findAll();
        }
    }

    public List<Order> getOrdersByClient(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            OrderDAO orderDAO = daoFactory.createOrderDAO(connection);
            return orderDAO.findByClientId(id);
        }
    }

    public long countOrderPrice(Order order) {
        long summary = 0;
        for (Periodical periodical : order.getPeriodicals()) {
            summary += periodical.getSubscriptionPrice();
        }
        return summary;
    }

    public boolean createNewOrder(Order order) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            OrderDAO orderDAO = daoFactory.createOrderDAO(connection);
            connection.begin();
            int result = orderDAO.insert(order);
            orderDAO.insertOrderDetails(order);
            connection.commit();
            return result != 0;
        }
    }
}