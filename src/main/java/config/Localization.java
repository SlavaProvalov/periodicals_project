package config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Provalov on 25.09.2017.
 */
public class Localization {
    private static String descriptionLanguage;
    private static ResourceBundle bundle;
    private static Locale currentLocale;

   static  {
        currentLocale = new Locale("en");
        bundle = ResourceBundle.getBundle("pageContext", currentLocale);
        descriptionLanguage = Localization.getInstance().getBundle().getString("descriptionLanguage");
    }

    private Localization() {
//
    }

    private static class Holder {
        static final Localization INSTANCE = new Localization();
    }

    public static Localization getInstance() {
        return Holder.INSTANCE;
    }

    public void setLocale(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle("pageContext", currentLocale);
        descriptionLanguage = Localization.getInstance().getBundle().getString("descriptionLanguage");
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public String getDescriptionLanguage() {
        return descriptionLanguage;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
