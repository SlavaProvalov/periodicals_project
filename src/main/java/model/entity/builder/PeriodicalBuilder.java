package model.entity.builder;

import model.entity.Periodical;

public class PeriodicalBuilder {
    private Periodical periodical;

    public PeriodicalBuilder createNewPeriodical() {
        this.periodical = new Periodical();
        return this;
    }

    public PeriodicalBuilder setId(int id) {
        this.periodical.setId(id);
        return this;
    }

    public PeriodicalBuilder setTitle(String title) {
        this.periodical.setTitle(title);
        return this;
    }

    public PeriodicalBuilder setPublicationFrequency(int frequency) {
        this.periodical.setPublicationFrequency(frequency);
        return this;
    }

    public PeriodicalBuilder setSubscriptionPrice(long price) {
        this.periodical.setSubscriptionPrice(price);
        return this;
    }

    public PeriodicalBuilder setRuDescription(String ruDescription) {
        this.periodical.setRuDescription(ruDescription);
        return this;
    }

    public PeriodicalBuilder setEnDescription(String enDescription) {
        this.periodical.setEnDescription(enDescription);
        return this;
    }

    public PeriodicalBuilder setDescriptions(String ruDescription, String enDescription) {
        this.periodical.putDescription("p_ru_description", ruDescription);
        this.periodical.putDescription("p_en_description", enDescription);
        return this;
    }

    public Periodical getPeriodical() {
        return this.periodical;
    }

}
