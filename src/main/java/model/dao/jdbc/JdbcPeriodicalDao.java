package model.dao.jdbc;

import config.StringConstants;
import model.dao.PeriodicalDAO;
import model.entity.Periodical;
import model.entity.builder.PeriodicalBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Provalov on 21.09.2017.
 */
public class JdbcPeriodicalDao implements PeriodicalDAO, StringConstants {
    private Connection connection;

    private String FIND_BY_ID = "SELECT * FROM `periodicals` WHERE `p_id`= ?;";

    private String FIND_BY_CLIENT_ID = "SELECT `p_id`,`p_title`,`p_publication_frequency`,`p_subscription_price`,`p_ru_description`, `p_en_description` " +
            "FROM `clients` LEFT JOIN `orders` ON `clients`.`c_id`= `orders`.`o_client_id` " +
            "LEFT JOIN `order_details` ON `orders`.`o_id` = `order_details`.`od_order_id` " +
            "LEFT JOIN `periodicals` ON `order_details`.`od_periodical_id` = `periodicals`.`p_id` " +
            "WHERE `c_id` = ?;";

    private String QUERY_FIND_ALL = "SELECT `p_id`,`p_title`, `p_publication_frequency`, `p_subscription_price`, `p_ru_description`, `p_en_description` FROM  `periodicals`;";

    private String UPDATE = "UPDATE `periodicals` " +
            "SET `p_title`= ?,`p_publication_frequency`= ?,`p_subscription_price`= ?, `p_ru_description`= ?, `p_en_description`= ?  WHERE `p_id` = ?";
    private String DELETE = "DELETE FROM `periodicals` WHERE `p_id`= ?";
    private String INSERT_PERIODICAL = "INSERT INTO `periodicals` (`p_title`, `p_publication_frequency`, `p_subscription_price`,`p_ru_description`,`p_en_description`) VALUES (?,?,?,?,?)";

    public JdbcPeriodicalDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Periodical> findAll() throws SQLException {
        List<Periodical> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(QUERY_FIND_ALL)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Periodical periodical = createPeriodicalFromRS(rs);
                result.add(periodical);
            }
        }
        return result;
    }

    @Override
    public List<Periodical> findByClientID(int id) throws SQLException {
        List<Periodical> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_CLIENT_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                result.add(createPeriodicalFromRS(rs));
            }
        }
        return result;
    }

    @Override
    public Optional<Periodical> findById(int id) throws SQLException {
        Optional<Periodical> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Periodical periodical = createPeriodicalFromRS(rs);
                result = Optional.of(periodical);
            }
        }
        return result;
    }

    private Periodical createPeriodicalFromRS(ResultSet rs) throws SQLException {
        PeriodicalBuilder periodicalBuilder = new PeriodicalBuilder();
        return periodicalBuilder.createNewPeriodical()
                .setId(rs.getInt(PERIODICAL_ID))
                .setTitle(rs.getString(PERIODICAL_TITLE))
                .setPublicationFrequency(rs.getInt(PERIODICAL_FREQUENCY))
                .setSubscriptionPrice(rs.getLong(PERIODICAL_SUBSCRIPTION_PRICE))
                .setRuDescription(rs.getString(PERIODICAL_RU_DESCRIPTION))
                .setEnDescription(rs.getString(PERIODICAL_EN_DESCRIPTION))
                .getPeriodical();

    }

    @Override
    public boolean update(Periodical periodical) throws SQLException {
        boolean result = false;
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            setParametersForUpdateOrInsertQuery(periodical, query);
            query.setInt(6, periodical.getId());
            if (query.executeUpdate() != 0) {
                result = true;
            }
        }
        return result;
    }


    @Override
    public boolean delete(int id) {
        return delete(connection, id, DELETE);
    }

    @Override
    public int insert(Periodical periodical) throws SQLException {
        int result = -1;
        try (PreparedStatement query = connection.prepareStatement(INSERT_PERIODICAL, Statement.RETURN_GENERATED_KEYS)) {
            setParametersForUpdateOrInsertQuery(periodical, query);
            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
                periodical.setId(result);
            }
        }
        return result;
    }

    private void setParametersForUpdateOrInsertQuery(Periodical periodical, PreparedStatement query) throws SQLException {
        query.setString(1, periodical.getTitle());
        query.setInt(2, periodical.getPublicationFrequency());
        query.setLong(3, periodical.getSubscriptionPrice());
        query.setString(4, periodical.getDescriptions().get(PERIODICAL_RU_DESCRIPTION));
        query.setString(5, periodical.getDescriptions().get(PERIODICAL_EN_DESCRIPTION));
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
