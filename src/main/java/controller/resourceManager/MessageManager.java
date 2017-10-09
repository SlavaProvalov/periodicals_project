package controller.resourceManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private ResourceBundle bundle;

    public MessageManager(String language) {
        bundle = ResourceBundle.getBundle("messages", new Locale(language));
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }

    public void changeLocale(String language) {
        this.bundle = ResourceBundle.getBundle("messages", new Locale(language));
    }
}
