package model.entity.builder;

import model.entity.Order;
import model.entity.Periodical;

import java.time.LocalDateTime;
import java.util.Collection;

public class OrderBuilder {
    public static Order createNewOrder(int clientId, String address, String city, String postalCode,
                                       String country, Collection<Periodical> periodicals) {
        return new Order(clientId, LocalDateTime.now(), address, city, postalCode, country, periodicals);
    }

}
