package model.dao.jdbc;

import config.StringConstants;
import model.dao.UserDAO;
import model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Provalov on 21.09.2017.
 */
public class JdbcUserDao implements UserDAO, StringConstants {

    private Connection connection;

    private final String FIND_BY_LOGIN = "SELECT * FROM `users` WHERE `u_login` = ?";
    private final String FIND_ALL = "SELECT * FROM `users`";
    private final String FIND_BY_ID = "SELECT * FROM `users` WHERE `u_id` = ?";
    private final String UPDATE = "UPDATE `users` SET `u_login`= ?, `u_password`= ?,`u_role`= ? WHERE `u_id`= ?";
    private final String INSERT = "INSERT INTO `users` (`u_login`, `u_password`, `u_role`) VALUES (?, ?, ?)";
    private final String DELETE = "DELETE FROM `users` WHERE `u_id`=?";


    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByLogin(String login) throws SQLException {
        Optional<User> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_LOGIN)) {
            query.setString(1, login.toLowerCase());
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                User user = createUser(rs);
                result = Optional.of(user);
            }
        }
        return result;
    }


    @Override
    public List<User> findAll() throws SQLException {
        List<User> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                User user = createUser(rs);
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                User user = createUser(rs);
                result = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(User user) throws SQLException {
        boolean result = false;
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            setParametersForUser(user, query);
            query.setInt(4, user.getId());
            if (query.executeUpdate() != 0) {
                result = true;
            }
        }
        return result;
    }

    private void setParametersForUser(User user, PreparedStatement query) throws SQLException {
        query.setString(1, user.getLogin().toLowerCase());
        query.setString(2, user.getHashPassword());
        query.setString(3, user.getRole().name().toLowerCase());
    }


    public boolean delete(int id) {
        return delete(connection, id, DELETE);
    }

    @Override
    public int insert(User user) throws SQLException {
        int result = -1;
        try (PreparedStatement query = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setParametersForUser(user, query);
            query.executeUpdate();
            ResultSet rsId = query.getGeneratedKeys();
            if (rsId.next()) {
                result = rsId.getInt(1);
                user.setId(result);
            }
        }
        return result;
    }

    private User createUser(ResultSet rs) throws SQLException {
        return new User(rs.getInt(USER_ID), rs.getString(USER_LOGIN),
                rs.getString(USER_PASS), rs.getString(USER_ROLE));
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
