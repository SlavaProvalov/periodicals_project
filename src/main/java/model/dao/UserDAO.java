package model.dao;

import model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Provalov on 21.09.2017.
 */
public interface UserDAO extends GenericDAO<User> {
    Optional<User> findByLogin(String login) throws SQLException;


}
