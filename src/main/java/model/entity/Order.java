package model.entity;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Provalov on 21.09.2017.
 */
public class Order {
    private int id;
    private int clientId;
    private LocalDateTime orderDate;
    private LocalDateTime orderEndDate;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    Collection<Periodical> periodicals;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(LocalDateTime orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Collection<Periodical> getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(Collection<Periodical> periodicals) {
        this.periodicals = periodicals;
    }

    public void addPeriodical(Periodical periodical) {
        this.periodicals.add(periodical);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != order.getId()) return false;
        if (getClientId() != order.getClientId()) return false;
        if (!getOrderDate().equals(order.getOrderDate())) return false;
        if (getAddress() != null ? !getAddress().equals(order.getAddress()) : order.getAddress() != null) return false;
        if (getCity() != null ? !getCity().equals(order.getCity()) : order.getCity() != null) return false;
        if (getPostalCode() != null ? !getPostalCode().equals(order.getPostalCode()) : order.getPostalCode() != null)
            return false;
        return getCountry() != null ? getCountry().equals(order.getCountry()) : order.getCountry() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getClientId();
        result = 31 * result + getOrderDate().hashCode();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getPostalCode() != null ? getPostalCode().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }
}
