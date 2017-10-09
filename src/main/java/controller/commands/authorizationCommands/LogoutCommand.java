package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import controller.resourceManager.PageContextManager;
import model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LogoutCommand implements ActionCommand {
    public LogoutCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MessageManager messageManager = (MessageManager) session.getAttribute("messageManager");
        PageContextManager pageContextManager = (PageContextManager) session.getAttribute("pageContextManager");
        String language = (String) session.getAttribute("language");
        session.invalidate();
        session = request.getSession(true);
        session.setAttribute("messageManager", messageManager);
        session.setAttribute("pageContextManager", pageContextManager);
        session.setAttribute("role", Role.GUEST.name());
        session.setAttribute("language", language);

        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.servlet.welcome"));
        return page.get();
    }
}
