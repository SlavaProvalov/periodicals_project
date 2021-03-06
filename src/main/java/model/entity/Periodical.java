package model.entity;

import config.StringConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Provalov on 21.09.2017.
 */
public class Periodical implements StringConstants {
    private int id;
    private String title;
    private int publicationFrequency;
    private long subscriptionPrice;
    private Map<String, String> descriptions = new HashMap<>();

    public Periodical() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationFrequency() {
        return publicationFrequency;
    }

    public void setPublicationFrequency(int publicationFrequency) {
        this.publicationFrequency = publicationFrequency;
    }

    public long getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(long subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public Map<String, String> getDescriptions() {
        return descriptions;
    }

    public String getRuDescription() {
        return descriptions.get("p_ru_description");
    }

    public String getEnDescription() {
        return descriptions.get("p_en_description");
    }

    public void setRuDescription(String ruDescription) {
        this.descriptions.put("p_ru_description", ruDescription);
    }

    public void setEnDescription(String enDescription) {
        this.descriptions.put("p_en_description", enDescription);
    }

    public void setDescriptions(Map<String, String> descriptions) {
        this.descriptions = descriptions;
    }

    public void putDescription(String descriptionLanguage, String description) { // TODO do not forget
        this.descriptions.put(descriptionLanguage, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Periodical)) return false;

        Periodical that = (Periodical) o;

        if (getId() != that.getId()) return false;
        if (getPublicationFrequency() != that.getPublicationFrequency()) return false;
        if (getSubscriptionPrice() != that.getSubscriptionPrice()) return false;
        return getTitle().equals(that.getTitle());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getPublicationFrequency();
        result = 31 * result + (int) (getSubscriptionPrice() ^ (getSubscriptionPrice() >>> 32));
        return result;
    }
}
