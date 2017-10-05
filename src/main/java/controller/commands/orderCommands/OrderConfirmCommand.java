package controller.commands.orderCommands;

import controller.SessionContent;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class OrderConfirmCommand implements ActionCommand {
    private static SessionContent sessionContent;

    public OrderConfirmCommand() {
        sessionContent = SessionContent.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        HttpSession session = request.getSession();
        try {
            setAttributes(request, session);
            session.setAttribute("items", PageContextManager.getProperty("cart.empty"));
            session.removeAttribute("cart");
            page = Optional.of(ConfigurationManager.getProperty("path.page.order_confirm"));
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: 02.10.2017 log
        }
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) throws SQLException {
        request.setAttribute("logout", PageContextManager.getProperty("authorization.logout"));
        request.setAttribute("main", PageContextManager.getProperty("main.menu"));
        request.setAttribute("hi", PageContextManager.getProperty("main.hi"));
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        request.setAttribute("user_details", PageContextManager.getProperty("main.user_details"));
        request.setAttribute("thankyou", PageContextManager.getProperty("order.thankyou"));
        request.setAttribute("caption", PageContextManager.getProperty("main.caption"));
        request.setAttribute("title", PageContextManager.getProperty("periodical.title"));
        request.setAttribute("frequency", PageContextManager.getProperty("periodical.frequency"));
        request.setAttribute("price", PageContextManager.getProperty("periodical.price"));
        request.setAttribute("description", PageContextManager.getProperty("periodical.description"));
        request.setAttribute("cartList", sessionContent.getBuyList());
        request.setAttribute("summary", PageContextManager.getProperty("cart.summary"));
        request.setAttribute("total_cost", session.getAttribute("total_cost"));
        request.setAttribute("order_address", PageContextManager.getProperty("order.address"));
        request.setAttribute("order_postalCode", PageContextManager.getProperty("order.postalCode"));
        request.setAttribute("order_city", PageContextManager.getProperty("order.city"));
        request.setAttribute("order_country", PageContextManager.getProperty("order.country"));
        request.setAttribute("address", request.getAttribute("address_value"));
        request.setAttribute("postalCode", request.getAttribute("postalCode_value"));
        request.setAttribute("city", request.getAttribute("city_value"));
        request.setAttribute("country", request.getAttribute("country_value"));

    }
}
