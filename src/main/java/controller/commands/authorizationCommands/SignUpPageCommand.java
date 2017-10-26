package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpPageCommand implements ActionCommand {
    private static SignUpPageCommand instance;

    private SignUpPageCommand() {
    }

    public static SignUpPageCommand getInstance() {
        if (instance == null) {
            instance = new SignUpPageCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        request.getSession().setAttribute("currentPage", request.getRequestURI());
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.sign_up"));
        return page;
    }

}
