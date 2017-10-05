package model.dao;

import model.entity.Client;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Provalov on 21.09.2017.
 */
public interface ClientDAO extends GenericDAO<Client> {
    Optional<Client> findByEmail(String email) throws SQLException;

}
