package model.entity.builder;

import model.entity.Order;
import model.entity.Periodical;

import java.time.LocalDateTime;
import java.util.Collection;

public class OrderBuilder {
    private Order order;

    public OrderBuilder createNewOrder() {
        this.order = new Order();
        return this;
    }

    public OrderBuilder setId(int id) {
        this.order.setId(id);
        return this;
    }

    public OrderBuilder setClientId(int clientId) {
        this.order.setClientId(clientId);
        return this;
    }

    public OrderBuilder setOrderDate(LocalDateTime date) {
        this.order.setOrderDate(date);
        return this;
    }

    public OrderBuilder setOrderEndDate(LocalDateTime date){
        this.order.setOrderEndDate(date);
        return this;
    }

    public OrderBuilder setAddress(String address) {
        this.order.setAddress(address);
        return this;
    }

    public OrderBuilder setCity(String city) {
        this.order.setCity(city);
        return this;
    }

    public OrderBuilder setPostalCode(String postalCode) {
        this.order.setPostalCode(postalCode);
        return this;
    }

    public OrderBuilder setCountry(String country) {
        this.order.setCountry(country);
        return this;
    }

    public OrderBuilder setPeriodicals(Collection<Periodical> periodicals) {
        this.order.setPeriodicals(periodicals);
        return this;
    }

    public Order getOrder() {
        return this.order;
    }

}
