package controller.commands.orderCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class OrderPageCommand implements ActionCommand {
    private static OrderPageCommand instance;

    private OrderPageCommand() {
    }

    public static OrderPageCommand getInstance() {
        if (instance == null) {
            instance = new OrderPageCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        request.getSession().setAttribute("currentPage", request.getRequestURI());
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.order"));
        return page;
    }

}
