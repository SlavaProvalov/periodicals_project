package controller.commands.orderCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class OrderPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("currentPage", request.getRequestURI());
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.order"));
        return page.get();
    }

}
