package controller.commands.userCommands;

import controller.SessionContent;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DeleteFromCart implements ActionCommand {
    private static SessionContent sessionContent;

    public DeleteFromCart() {
        sessionContent = SessionContent.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("Item_id"));
        sessionContent.getBuyList().remove(sessionContent.getPeriodicals().get(id));
        request.getSession().setAttribute("cart", sessionContent.getBuyList());
        request.getSession().setAttribute("items", sessionContent.getBuyList().size());
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.servlet.cart"));
        return page.get();
    }
}
