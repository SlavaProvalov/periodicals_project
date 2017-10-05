package controller.commands.orderCommands;

import controller.SessionContent;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import model.entity.builder.OrderBuilder;
import model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class OrderCommand implements ActionCommand {
    private static SessionContent sessionContent;
    private static OrderService service;

    public OrderCommand() {
        sessionContent = SessionContent.getInstance();
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
            request.setAttribute("address_value", address);
            request.setAttribute("postalCode_value", postalCode);
            request.setAttribute("city_value", city);
            request.setAttribute("country_value", country);
            service.createNewOrder(OrderBuilder.createNewOrder(id, address, city, postalCode, country, sessionContent.getBuyList()));
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.order_confirm"));
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: 02.10.2017 log
        }
        return page.get();
    }
}
