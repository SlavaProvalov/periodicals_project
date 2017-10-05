package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Provalov on 21.09.2017.
 */
public interface GenericDAO<T> extends AutoCloseable {

    List<T> findAll() throws SQLException;

    Optional<T> findById(int id) throws SQLException;

    boolean update(T item) throws SQLException;

    boolean delete(int id);

    default boolean delete(Connection connection, int id, String statement) {
        boolean result = false;
        try (PreparedStatement query = connection.prepareStatement(statement)) {
            query.setInt(1, id);
            if (query.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    int insert(T item) throws SQLException;
}
