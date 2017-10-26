package controller.commands;

import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ErrorCommand implements ActionCommand {
    private static ErrorCommand instance;

    private ErrorCommand() {
    }

    public static ErrorCommand getInstance() {
        if (instance == null) {
            instance = new ErrorCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        request.setAttribute("errorCode", 403);
        request.setAttribute("errorPath", request.getAttribute("restrictedPage"));
        request.setAttribute("errorRole", request.getSession().getAttribute("role"));
        request.setAttribute("redirectServlet", ConfigurationManager.getProperty((String) request.getAttribute("redirectPage")));
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
        return page;
    }
}
