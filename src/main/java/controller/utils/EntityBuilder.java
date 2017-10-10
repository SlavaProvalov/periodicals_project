package controller.utils;

import model.entity.Order;
import model.entity.Periodical;
import model.entity.builder.OrderBuilder;
import model.entity.builder.PeriodicalBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Set;

public class EntityBuilder {

    public static Periodical createPeriodicalFromRequest(HttpServletRequest request) {
        String title = request.getParameter("title");
        int frequency = Integer.parseInt(request.getParameter("frequency"));
        long price = StringConverter.stringPriceToLong(request);
        String ruDescription = request.getParameter("ruDescription");
        String enDescription = request.getParameter("enDescription");
        return new PeriodicalBuilder()
                .createNewPeriodical()
                .setTitle(title)
                .setPublicationFrequency(frequency)
                .setSubscriptionPrice(price)
                .setRuDescription(ruDescription)
                .setEnDescription(enDescription)
                .getPeriodical();
    }

    public static Order createOrderFromRequest(HttpServletRequest request, HttpSession session) {
        int id = (int) session.getAttribute("userId");
        String address = request.getParameter("address");
        String postalCode = request.getParameter("postalCode");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        session.setAttribute("address_value", address);
        session.setAttribute("postalCode_value", postalCode);
        session.setAttribute("city_value", city);
        session.setAttribute("country_value", country);
        OrderBuilder builder = new OrderBuilder();
        Order order = builder.createNewOrder()
                .setClientId(id)
                .setOrderDate(LocalDateTime.now())
                .setAddress(address)
                .setCity(city)
                .setPostalCode(postalCode)
                .setCountry(country)
                .setPeriodicals((Set<Periodical>) session.getAttribute("cart")).getOrder();
        return order;
    }
}
