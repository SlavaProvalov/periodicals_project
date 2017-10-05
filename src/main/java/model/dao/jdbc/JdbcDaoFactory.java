package model.dao.jdbc;

import config.Config;
import model.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Provalov on 23.09.2017.
 */
public class JdbcDaoFactory extends DaoFactory {

    public DaoConnection getConnection() {
        Config config = Config.getInstance();
        Connection connection;
        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPass());
            return new JdbcDaoConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @Override
    public ClientDAO createClientDAO(DaoConnection connection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcDaoConnection.getConnection();
        return new JdbcClientDao(sqlConnection);
    }

    @Override
    public UserDAO createUserDAO(DaoConnection connection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcDaoConnection.getConnection();
        return new JdbcUserDao(sqlConnection);
    }

    @Override
    public OrderDAO createOrderDAO(DaoConnection connection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcDaoConnection.getConnection();
        return new JdbcOrderDao(sqlConnection);
    }

    @Override
    public PeriodicalDAO createPeriodicalDAO(DaoConnection connection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcDaoConnection.getConnection();
        return new JdbcPeriodicalDao(sqlConnection);
    }
}
