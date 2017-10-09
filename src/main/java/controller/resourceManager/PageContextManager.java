package controller.resourceManager;

import java.util.Locale;
import java.util.ResourceBundle;


public class PageContextManager {
    private ResourceBundle bundle;

    public PageContextManager(String language) {
        bundle = ResourceBundle.getBundle("pageContext", new Locale(language));
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }

    public void changeLocale(String language) {
        this.bundle = ResourceBundle.getBundle("pageContext", new Locale(language));
    }

}
