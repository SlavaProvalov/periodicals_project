package controller.commands.authorizationCommands;

import controller.SessionContent;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        SessionContent sessionContent = SessionContent.getInstance();
        sessionContent.getBuyList().clear();
        sessionContent.getUserPeriodicals().clear();
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.servlet.welcome"));
        request.getSession().invalidate();
        return page.get();
    }
}
