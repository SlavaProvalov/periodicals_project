package controller.resourceManager;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("config");
    }

    private ConfigurationManager() {

    }

    public static String getProperty(String key) {
        return bundle.getString(key);
    }


}
