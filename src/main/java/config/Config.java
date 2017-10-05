package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Provalov on 21.09.2017.
 */
public class Config {
    private String url;
    private String user;
    private String pass;
    private String factoryClassName;

    private Config() {
        load();
    }

    private static class Holder {
        private static Config INSTANCE = new Config();
    }

    public static Config getInstance() {
        return Holder.INSTANCE;
    }

    private void load() {

        try (InputStream in = this.getClass().getResourceAsStream("/db.properties")) {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(in);
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.pass");
            url = properties.getProperty("db.url");
            factoryClassName = properties.getProperty("db.factory.class");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getFactoryClassName() {
        return factoryClassName;
    }
}
