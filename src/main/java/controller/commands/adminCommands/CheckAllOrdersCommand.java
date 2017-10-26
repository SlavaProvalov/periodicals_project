package controller.commands.adminCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;
import model.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class CheckAllOrdersCommand implements ActionCommand {
    private static CheckAllOrdersCommand instance;
    private static Logger log;
    private static OrderService service;

    private CheckAllOrdersCommand() {
        log = Logger.getLogger(CheckAllOrdersCommand.class);
        service = OrderService.getInstance();
    }

    public static CheckAllOrdersCommand getInstance() {
        if (instance == null) {
            instance = new CheckAllOrdersCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession();
        try {
            request.setAttribute("ordersList", service.getAllOrders());
            session.setAttribute("currentPage", ConfigurationManager.getProperty("path.servlet.check_all_orders"));
            page = Optional.of(ConfigurationManager.getProperty("path.page.check_all_orders"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.logout");
        }
        return page;
    }
}
