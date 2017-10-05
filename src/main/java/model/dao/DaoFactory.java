package model.dao;

import config.Config;

/**
 * Created by Provalov on 23.09.2017.
 */
public abstract class DaoFactory {
    public abstract DaoConnection getConnection();
//
//    public abstract ClientDAO createClintDAO();
//
//    public abstract UserDAO createUserDAO();
//
//    public abstract OrderDAO createOrderDAO();

//    public abstract PeriodicalDAO createPeriodicalDAO();

    public abstract ClientDAO createClientDAO(DaoConnection connection);

    public abstract UserDAO createUserDAO(DaoConnection connection);

    public abstract OrderDAO createOrderDAO(DaoConnection connection);

    public abstract PeriodicalDAO createPeriodicalDAO(DaoConnection connection);

    public static DaoFactory getInstance() {
        String classname = Config.getInstance().getFactoryClassName();
        DaoFactory factory = null;
        try {
            factory = (DaoFactory) Class.forName(classname).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return factory;
    }
}
