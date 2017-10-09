package controller.commands.orderCommands;

import controller.ContextStorage;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class OrderConfirmCommand implements ActionCommand {
    private static ContextStorage contextStorage;

    public OrderConfirmCommand() {
        contextStorage = ContextStorage.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        HttpSession session = request.getSession();
        try {
            setAttributes(request, session);
            session.removeAttribute("items");
            session.removeAttribute("cart");
            page = Optional.of(ConfigurationManager.getProperty("path.page.order_confirm"));
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: 02.10.2017 log
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorMessage(request,e,"path.servlet.order_page");
        }
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) throws SQLException {
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        request.setAttribute("cartList", session.getAttribute("cart"));
        session.setAttribute("currentPage", request.getRequestURI());
    }
}
