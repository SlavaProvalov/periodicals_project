package controller.commands.orderCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class OrderConfirmCommand implements ActionCommand {
    private static final Logger log = Logger.getLogger(OrderConfirmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession();
        try {
            setAttributes(request, session);
            session.removeAttribute("items");
            session.removeAttribute("cart");
            page = Optional.of(ConfigurationManager.getProperty("path.page.order_confirm"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.order_page");
        }
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) throws SQLException {
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        request.setAttribute("cartList", session.getAttribute("cart"));
        session.setAttribute("currentPage", request.getRequestURI());
    }
}
