package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        setAttributes(request);
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.signUp"));
        return page.get();
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute("login", PageContextManager.getProperty("signUp.login"));
        request.setAttribute("password", PageContextManager.getProperty("signUp.password"));
        request.setAttribute("email", PageContextManager.getProperty("signUp.email"));
        request.setAttribute("name", PageContextManager.getProperty("signUp.name"));
        request.setAttribute("lastName", PageContextManager.getProperty("signUp.lastName"));
        request.setAttribute("phone", PageContextManager.getProperty("signUp.phone"));
        request.setAttribute("pattern_login", PageContextManager.getProperty("pattern.login"));
        request.setAttribute("pattern_password", PageContextManager.getProperty("pattern.password"));
        request.setAttribute("pattern_email", PageContextManager.getProperty("pattern.email"));
        request.setAttribute("pattern_name", PageContextManager.getProperty("pattern.name"));
        request.setAttribute("pattern_phone", PageContextManager.getProperty("pattern.phone"));
        request.setAttribute("phone_ph", PageContextManager.getProperty("phone"));
        request.setAttribute("submit", PageContextManager.getProperty("signUp.submit"));
    }
}
