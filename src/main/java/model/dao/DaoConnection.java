package model.dao;

import java.sql.SQLException;

/**
 * Created by Provalov on 25.09.2017.
 */
public interface DaoConnection extends AutoCloseable {
    void begin() throws SQLException;
    void commit() throws SQLException;
    void rollback() throws SQLException;
    void close();
}
