package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        setAttributes(request);
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.login"));
        return page.get();
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("login", PageContextManager.getProperty("login.login"));
        request.setAttribute("password", PageContextManager.getProperty("login.password"));
        request.setAttribute("submit", PageContextManager.getProperty("login.submit"));
        request.setAttribute("pattern_login", PageContextManager.getProperty("pattern.login"));
        request.setAttribute("pattern_password", PageContextManager.getProperty("pattern.password"));
    }
}
