package model.entity.builder;

import model.entity.Periodical;

public class PeriodicalBuilder {
    public static Periodical createNewPeriodical(String title, int frequency, long price,
                                                 String ruDescription, String enDescription) {
        return new Periodical(title, frequency, price, ruDescription, enDescription);
    }

    public static Periodical createPeriodicalFromDB(int id, String title, int frequency, long price,
                                                    String description, String language) {
        return new Periodical(id, title, frequency, price, description, language);
    }

}
