package model.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.PeriodicalDAO;
import model.entity.Periodical;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Provalov on 26.09.2017.
 */
public class PeriodicalService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private PeriodicalService() {
    }

    private static class Holder {
        static final PeriodicalService INSTANCE = new PeriodicalService();
    }

    public static PeriodicalService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Periodical> getAllPeriodicals() throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PeriodicalDAO dao = daoFactory.createPeriodicalDAO(connection);
            return dao.findAll();
        }
    }

    public List<Periodical> getPeriodicalsByClientId(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PeriodicalDAO dao = daoFactory.createPeriodicalDAO(connection);
            return dao.findByClientID(id);
        }
    }

    public List<Periodical> getPeriodicalsByOrderId(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PeriodicalDAO dao = daoFactory.createPeriodicalDAO(connection);
            return dao.findByOrderId(id);
        }

    }

    public int createNewPeriodical(Periodical periodical) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PeriodicalDAO dao = daoFactory.createPeriodicalDAO(connection);
            return dao.insert(periodical);
        }
    }

    public long countTotalPrice(Collection<Periodical> periodicals) {
        long totalPrice = 0;
        for (Periodical periodical : periodicals) {
            totalPrice += periodical.getSubscriptionPrice();
        }
        return totalPrice;
    }

}
