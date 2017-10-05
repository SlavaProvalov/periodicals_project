package controller.resourceManager;

import config.Localization;

import java.util.ResourceBundle;

public class MessageManager {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages", Localization.getInstance().getCurrentLocale());

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
