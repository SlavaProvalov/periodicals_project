package controller.commands.orderCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;
import model.entity.Periodical;
import model.entity.builder.OrderBuilder;
import model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class OrderCommand implements ActionCommand {
    private static OrderService service;

    public OrderCommand() {
        service = OrderService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        HttpSession session = request.getSession();
        try {
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
            service.createNewOrder(builder.createNewOrder()
                    .setClientId(id)
                    .setOrderDate(LocalDateTime.now())
                    .setAddress(address)
                    .setCity(city)
                    .setPostalCode(postalCode)
                    .setCountry(country)
                    .setPeriodicals((Set<Periodical>) session.getAttribute("cart")).getOrder());
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.order_confirm"));
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: 02.10.2017 log
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorMessage(request, e, "path.servlet.cart");
        }
        return page.get();
    }
}
