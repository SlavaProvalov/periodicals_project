package model.dao;

import model.entity.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Provalov on 21.09.2017.
 */
public interface OrderDAO extends GenericDAO<Order> {

    List<Order> findByClientId(int id) throws SQLException;

    void insertOrderDetails(Order order) throws SQLException;
}
