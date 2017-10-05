package controller.resourceManager;

import config.Localization;

import java.util.ResourceBundle;


public class PageContextManager {
    private static final ResourceBundle bundle =
            ResourceBundle.getBundle("pageContext", Localization.getInstance().getCurrentLocale());

    private PageContextManager() {
    }

    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
