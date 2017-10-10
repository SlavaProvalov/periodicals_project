package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;
import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;
import model.entity.User;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;

public class LoginCommand implements ActionCommand {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static UserService userService;


    public LoginCommand() {
        userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession(true);
        try {
            String login = request.getParameter(PARAM_NAME_LOGIN);
            String password = request.getParameter(PARAM_NAME_PASSWORD);
            saveValuesInFields(request, login, password);
            Optional<User> user = userService.login(login, password);
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("userLogin", login);
            session.setAttribute("role", user.get().getRole().name());
            session.setAttribute("cart", new HashSet<>());
            session.setMaxInactiveInterval(30 * 60);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.main"));
        } catch (UserNotFoundException e) {
            log.info(e);
            request.setAttribute("errorLogin", 1);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.login_page"));
        } catch (InvalidPasswordException e) {
            request.setAttribute("errorPassword", 1);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.login_page"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.login_page");
        }
        return page.get();
    }

    private void saveValuesInFields(HttpServletRequest request, String login, String password) {
        request.setAttribute("errorPassword", 0);
        request.setAttribute("errorLogin", 0);
        request.setAttribute("login_value", login);
        request.setAttribute("password_value", password);
    }

}
