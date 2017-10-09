package controller.commands.userCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UserUpdatePageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        request.setAttribute("firstName_prev", session.getAttribute("firstName"));
        request.setAttribute("lastName_prev", session.getAttribute("lastName"));
        request.setAttribute("email_prev", session.getAttribute("email"));
        request.setAttribute("phone_prev", session.getAttribute("phoneNumber"));
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.user_update"));
        return page.get();
    }
}
