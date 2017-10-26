package controller.commands.orderCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.EntityBuilder;
import controller.utils.ErrorConstructor;
import model.entity.Order;
import model.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class OrderCommand implements ActionCommand {
    private static OrderCommand instance;
    private static Logger log;
    private static OrderService service;

    private OrderCommand() {
        service = OrderService.getInstance();
        log = Logger.getLogger(OrderCommand.class);
    }

    public static OrderCommand getInstance() {
        if (instance == null) {
            instance = new OrderCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession();
        try {
            Order order = EntityBuilder.createOrderFromRequest(request, session);
            int month = Integer.parseInt(request.getParameter("months"));
            int discount = service.countDiscount(month);
            long totalPrice = service.countOrderPrice(order, discount);
            session.setAttribute("discount", discount);
            session.setAttribute("total_cost", totalPrice);
            service.createNewOrder(order);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.order_confirm"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.cart");
        }
        return page;
    }
}
