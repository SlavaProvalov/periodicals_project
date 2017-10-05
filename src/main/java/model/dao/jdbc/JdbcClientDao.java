package model.dao.jdbc;

import config.StringConstants;
import model.dao.*;
import model.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Provalov on 21.09.2017.
 */
public class JdbcClientDao implements ClientDAO, StringConstants {

    private Connection connection;

    private final String FIND_ALL = "SELECT * FROM `clients`";
    private final String FIND_BY_ID = "SELECT * FROM `clients` WHERE `c_id`= ?";
    private final String FIND_BY_EMAIL = "SELECT * FROM `clients` WHERE `c_email` = ?";
    private final String UPDATE = "UPDATE `clients` SET `c_firstName`= ?,`c_lastName`= ?,`c_email`= ?,`c_phoneNumber`= ? WHERE `c_id`= ?";
    private final String DELETE = "DELETE FROM `clients` WHERE `c_id`=?";
    private final String INSERT = "INSERT INTO `clients` (`c_id`,`c_firstName`,`c_lastName`,`c_email`,`c_phoneNumber`,`c_registrationDate`) VALUES (?,?,?,?,?,?)";


    public JdbcClientDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Client> findAll() throws SQLException {
        List<Client> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Client client = createClientFromRS(rs);
                result.add(client);
            }
            return result;
        }
    }


    @Override
    public Optional<Client> findById(int id) throws SQLException {
        Optional<Client> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Client client = createClientFromRS(rs);
                result = Optional.of(client);
            }
        }
        return result;
    }

    @Override
    public Optional<Client> findByEmail(String email) throws SQLException {
        Optional<Client> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_EMAIL)) {
            query.setString(1, email);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Client client = createClientFromRS(rs);
                result = Optional.of(client);
            }
        }
        return result;
    }

    @Override
    public boolean update(Client client) throws SQLException {
        boolean result = false;
        try (PreparedStatement query = connection.
                prepareStatement(UPDATE)) {
            query.setString(1, client.getFirstName());
            query.setString(2, client.getLastName());
            query.setString(3, client.getEmail());
            query.setString(4, client.getPhoneNumber());
            query.setInt(5, client.getId());
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
    public int insert(Client client) throws SQLException {
        int result = -1;
        try (PreparedStatement query = connection
                .prepareStatement(INSERT)) {
            query.setInt(1, client.getId());
            query.setString(2, client.getFirstName());
            query.setString(3, client.getLastName());
            query.setString(4, client.getEmail());
            query.setString(5, client.getPhoneNumber());
            query.setDate(6, Date.valueOf(client.getRegistrationDate()));

            result = query.executeUpdate();
        }
        return result;
    }

    private Client createClientFromRS(ResultSet rs) throws SQLException {
        return new Client(rs.getInt(CLIENT_ID),
                rs.getString(CLIENT_FIRST_NAME), rs.getString(CLIENT_LAST_NAME),
                rs.getString(CLIENT_EMAIL), rs.getString(CLIENT_PHONE_NUMBER),
                rs.getDate(CLIENT_REGISTRATION_DATE).toLocalDate());
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
