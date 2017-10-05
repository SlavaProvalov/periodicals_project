package controller.commands.userCommands;

import controller.SessionContent;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AddToCartCommand implements ActionCommand {
    private static SessionContent sessionContent;

    public AddToCartCommand() {
        sessionContent = SessionContent.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int periodicalId = Integer.parseInt(request.getParameter("pItem_id"));
        if (!sessionContent.getUserPeriodicals().contains(sessionContent.getPeriodicals().get(periodicalId))) {
            sessionContent.getBuyList().add(sessionContent.getPeriodicals().get(periodicalId));
            session.setAttribute("items", sessionContent.getBuyList().size());
            session.setAttribute("cart", sessionContent.getBuyList());
        }
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.servlet.main"));
        return page.get();
    }


}
