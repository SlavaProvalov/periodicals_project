package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginPageCommand implements ActionCommand {
    private static LoginPageCommand instance;

    private LoginPageCommand() {
    }

    public static LoginPageCommand getInstance() {
        if (instance == null) {
            instance = new LoginPageCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        request.getSession().setAttribute("currentPage", request.getRequestURI());
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.login"));
        return page;
    }


}
