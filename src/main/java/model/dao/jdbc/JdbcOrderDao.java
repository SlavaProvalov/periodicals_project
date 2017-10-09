package model.dao.jdbc;

import config.StringConstants;
import model.dao.OrderDAO;
import model.entity.Order;
import model.entity.Periodical;
import model.entity.builder.OrderBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Provalov on 21.09.2017.
 */
public class JdbcOrderDao implements OrderDAO, StringConstants {

    private Connection connection;

    private final String FIND_ALL = "SELECT * FROM `orders`";
    private final String FIND_BY_ID = "SELECT * FROM `orders` WHERE `o_id`=?";
    /* private final String FIND_BY_ID_WIT_DETAILS = "SELECT `o_id`, `o_client_id`,`o_order_date`,`o_order_address`,`o_order_city`,`o_order_postalCode`," +
             " `o_order_country`,`p_id`,`p_title`,`p_publication_frequency`,`p_subscription_price` " +
             "FROM `orders` " +
             "JOIN `order_details` ON `orders`.`o_id` = `order_details`.`od_order_id` " +
             "JOIN `periodicals` ON `order_details`.`od_periodical_id` = `periodicals`.`p_id` " +
             "WHERE `orders`.`o_id` = ?";*/
    private final String FIND_BY_CLIENT_ID = "SELECT * FROM `orders` WHERE `o_client_id`= ?";
    private final String UPDATE = "UPDATE `orders` SET `o_client_id`= ?,`o_order_date`= ?,`o_order_address`= ?,`o_order_city`= ?, `o_order_postalCode`=?, `o_order_country`= ? WHERE `o_id`= ?";
    private final String DELETE = "DELETE FROM `orders` WHERE `o_id`= ?";
    private final String INSERT = "INSERT INTO `orders` (`o_client_id`,`o_order_date`,`o_order_address`,`o_order_city`,`o_order_postalCode`,`o_order_country`) VALUES (?,?,?,?,?,?)";
    private final String INSERT_ORDER_DETAILS = "INSERT INTO `order_details` (`od_order_id`,`od_periodical_id`) VALUES (?,?)";

    public JdbcOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> result = new ArrayList<>();
        try (PreparedStatement orderQuery = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = orderQuery.executeQuery();
            while (rs.next()) {
                Order order = createOrderFromRS(rs);
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public List<Order> findByClientId(int id) throws SQLException {
        List<Order> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_CLIENT_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Order order = createOrderFromRS(rs);
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Optional<Order> findById(int id) throws SQLException {
        Optional<Order> result = Optional.empty();
        try (PreparedStatement orderQuery = connection.prepareStatement(FIND_BY_ID)) {
            orderQuery.setInt(1, id);
            ResultSet rs = orderQuery.executeQuery();
            if (rs.next()) {
                Order order = createOrderFromRS(rs);
                result = Optional.of(order);
            }
        }
        return result;
    }


    @Override
    public boolean update(Order order) throws SQLException {
        boolean result = false;
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            sendOrder(order, query);
            query.setInt(7, order.getId());
            if (query.executeUpdate() != 0) {
                result = true;
            }
        }
        return result;
    }


    public boolean delete(int id) {
        return delete(connection, id, DELETE);
    }

    @Override
    public int insert(Order order) throws SQLException {
        int result = -1;
        try (PreparedStatement query = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            sendOrder(order, query);
            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
                order.setId(result);
            }
        }
        return result;
    }

    @Override
    public void insertOrderDetails(Order order) throws SQLException {
        try (PreparedStatement query = connection.prepareStatement(INSERT_ORDER_DETAILS)) {
            for (Periodical periodical : order.getPeriodicals()) {
                sendOrderDetails(order, periodical, query);
                query.executeUpdate();
            }
        }
    }

    private void sendOrderDetails(Order order, Periodical periodical, PreparedStatement query) throws SQLException {
        query.setInt(1, order.getId());
        query.setInt(2, periodical.getId());
    }

    private void sendOrder(Order order, PreparedStatement query) throws SQLException {
        query.setInt(1, order.getClientId());
        query.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
        query.setString(3, order.getAddress());
        query.setString(4, order.getCity());
        query.setString(5, order.getPostalCode());
        query.setString(6, order.getCountry());
    }


    private Order createOrderFromRS(ResultSet rs) throws SQLException {
        OrderBuilder orderBuilder = new OrderBuilder();
        return orderBuilder.createNewOrder().setId(rs.getInt(ORDER_ID))
                .setClientId(rs.getInt(ORDER_CLIENT_ID))
                .setOrderDate(rs.getTimestamp(ORDER_DATE).toLocalDateTime())
                .setAddress(rs.getString(ORDER_ADDRESS))
                .setCity(rs.getString(ORDER_CITY))
                .setPostalCode(rs.getString(ORDER_POSTAL_CODE))
                .setCountry(rs.getString(ORDER_COUNTRY)).getOrder();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
