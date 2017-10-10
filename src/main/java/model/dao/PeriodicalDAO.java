package model.dao;

import model.entity.Periodical;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Provalov on 21.09.2017.
 */
public interface PeriodicalDAO extends GenericDAO<Periodical> {

    List<Periodical> findByClientID(int id) throws SQLException;

    List<Periodical> findByOrderId(int id) throws SQLException;
}
