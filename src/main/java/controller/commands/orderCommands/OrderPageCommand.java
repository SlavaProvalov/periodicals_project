package controller.commands.orderCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class OrderPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        setAttributes(request);
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.order"));
        return page.get();
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("address", PageContextManager.getProperty("order.address"));
        request.setAttribute("postalCode", PageContextManager.getProperty("order.postalCode"));
        request.setAttribute("city", PageContextManager.getProperty("order.city"));
        request.setAttribute("country", PageContextManager.getProperty("order.country"));
        request.setAttribute("pattern_address", PageContextManager.getProperty("pattern.address"));
        request.setAttribute("pattern_postalCode", PageContextManager.getProperty("pattern.postalCode"));
        request.setAttribute("pattern_city", PageContextManager.getProperty("pattern.city"));
        request.setAttribute("pattern_country", PageContextManager.getProperty("pattern.country"));
        request.setAttribute("submit", PageContextManager.getProperty("order.submit"));
    }
}
