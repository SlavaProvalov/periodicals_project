package controller.commands.orderCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CartCommand implements ActionCommand {
    private static CartCommand instance;

    private CartCommand() {
    }

    public static CartCommand getInstance() {
        if (instance == null) {
            instance = new CartCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setAttributes(request, session);
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.cart"));
        return page;
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) {
        request.setAttribute("cartList", session.getAttribute("cart"));
        session.setAttribute("currentPage", request.getRequestURI());
    }


}
